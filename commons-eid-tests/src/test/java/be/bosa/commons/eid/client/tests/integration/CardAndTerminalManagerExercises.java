/*
 * Commons eID Project.
 * Copyright (C) 2014 - 2018 BOSA.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 3.0 as published by
 * the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, see https://www.gnu.org/licenses/.
 */

package be.bosa.commons.eid.client.tests.integration;

import be.bosa.commons.eid.client.CardAndTerminalManager;
import be.bosa.commons.eid.client.event.CardEventsListener;
import be.bosa.commons.eid.client.event.CardTerminalEventsListener;
import org.junit.Test;

import javax.smartcardio.Card;
import javax.smartcardio.CardTerminal;
import java.util.Random;

public class CardAndTerminalManagerExercises implements CardTerminalEventsListener, CardEventsListener {

	/**
	 * Exercises asynchronous run with callbacks: instantiate, register
	 * listeners, call start(). The test then loops and adds/removes a listener
	 * in some pseudo-random timing pattern. This is to ensure that the list of
	 * listeners remains properly synchronized in relation to it being iterated
	 * whenever events are being sent to listeners this test never returns..
	 * since it requires someone to attach/detach readers and insert/remove
	 * cards this is no problem until we automate those using e.g.
	 * http://www.lynxmotion
	 * .com/p-816-al5d-robotic-arm-combo-kit-free-software.aspx
	 *
	 * While running this test, the operator should attach and detach at least 2
	 * card terminals, insert and remove cards from them, in all possible
	 * permutations. The state displayed should, at all times, reflect the state
	 * of the readers and their cards within 250-400 ms.
	 */
	@Test
	public void testDetections() throws InterruptedException {
		Random random = new Random(0);

		CardAndTerminalManager cardAndTerminalManager = new CardAndTerminalManager(new TestLogger());
		cardAndTerminalManager.addCardTerminalListener(this);
		cardAndTerminalManager.addCardListener(this);
		cardAndTerminalManager.start();

		CardTerminalEventsListener dummyCTL = new CardTerminalEventsListener() {
			@Override
			public void terminalDetached(CardTerminal cardTerminal) {
			}

			@Override
			public void terminalAttached(CardTerminal cardTerminal) {
			}

			@Override
			public void terminalEventsInitialized() {
			}
		};

		CardEventsListener dummyCL = new CardEventsListener() {
			@Override
			public void cardRemoved(CardTerminal cardTerminal) {
			}

			@Override
			public void cardInserted(CardTerminal cardTerminal, Card card) {
			}

			@Override
			public void cardEventsInitialized() {

			}
		};

		System.err.println("main thread running.. do some card tricks..");

		//noinspection InfiniteLoopStatement
		for (; ; ) {
			System.err.print("+");
			cardAndTerminalManager.addCardTerminalListener(dummyCTL);
			cardAndTerminalManager.addCardListener(dummyCL);
			Thread.sleep(random.nextInt(100));
			System.err.print("-");
			cardAndTerminalManager.removeCardTerminalListener(dummyCTL);
			cardAndTerminalManager.removeCardListener(dummyCL);
			Thread.sleep(random.nextInt(100));
		}
	}

	/**
	 * Exercise CardAndTerminalManager's start() stop() semantics, with regards
	 * to its worker thread. This test starts and stops a CardAndTerminalManager
	 * randomly. It should remain in a consistent state at all times and detect
	 * terminal attaches/detaches and card inserts/removals as usual (while
	 * running, of course..)
	 */
	@Test
	public void testStartStop() throws Exception {
		Random random = new Random(0);
		CardAndTerminalManager cardAndTerminalManager = new CardAndTerminalManager(new TestLogger());
		cardAndTerminalManager.addCardTerminalListener(this);
		cardAndTerminalManager.addCardListener(this);
		cardAndTerminalManager.start();

		//noinspection InfiniteLoopStatement
		for (; ; ) {
			System.err.print("+");
			cardAndTerminalManager.start();
			Thread.sleep(random.nextInt(2000));
			System.err.print("-");
			cardAndTerminalManager.stop();
			Thread.sleep(random.nextInt(2000));
		}
	}

	@Override
	public void terminalAttached(CardTerminal terminalAttached) {
		System.err.println("Terminal Attached [" + terminalAttached.getName() + "]");
	}

	@Override
	public void terminalDetached(CardTerminal terminalDetached) {
		System.err.println("Terminal Detached [" + terminalDetached.getName() + "]");
	}

	@Override
	public void cardInserted(CardTerminal cardTerminal, Card card) {
		if (card != null) {
			System.err.println("Card [" + StringUtils.atrToString(card.getATR()) + "] Inserted Into Terminal [" + cardTerminal.getName() + "]");
		} else {
			System.err.println("Card present but failed to connect()");
		}
	}

	@Override
	public void cardRemoved(CardTerminal terminalWithCardRemoved) {
		System.err.println("Card Removed From [" + terminalWithCardRemoved.getName() + "]");
	}

	@Override
	public void cardEventsInitialized() {
		System.out.println("Card Events Initialised");
	}

	@Override
	public void terminalEventsInitialized() {
		System.out.println("Terminal Events Initialised");
	}
}
