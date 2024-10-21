package org.example;

import java.util.ArrayList;

/**
 * Represents a dataset containing a collection of events.
 *
 * @param events a list of {@link Event} objects associated with the dataset
 */
public record Dataset(ArrayList<Event> events) {
}
