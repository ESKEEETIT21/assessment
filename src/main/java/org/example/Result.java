package org.example;

import java.util.Collection;

/**
 * Represents the result containing a collection of customers.
 *
 * @param result a collection of {@link Customer} objects
 */
public record Result(Collection<Customer> result) {
}