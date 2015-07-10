/*
 * Copyright 2012 Daniel Bechler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.danielbechler.diff.selector;

import de.danielbechler.diff.comparison.EqualsIdentityStrategy;
import de.danielbechler.diff.comparison.IdentityStrategy;
import de.danielbechler.util.Assert;
import de.danielbechler.util.Strings;

/**
 * @author Daniel Bechler
 */
public final class CollectionItemElementSelector extends ElementSelector
{
	private final Object item;
	private final IdentityStrategy identityStrategy;

	/**
	 * Default implementation uses IdentityService.EQUALS_IDENTITY_STRATEGY.
	 *
	 * @param item
	 */
	public CollectionItemElementSelector(final Object item)
	{
		this.item = item;
		this.identityStrategy = EqualsIdentityStrategy.getInstance();
	}

	/**
	 * Allows for custom IdentityStrategy.
	 *
	 * @param item
	 * @param identityStrategy
	 */
	public CollectionItemElementSelector(final Object item,
										 final IdentityStrategy identityStrategy)
	{
		this.item = item;
		Assert.notNull(identityStrategy, "identityStrategy");
		this.identityStrategy = identityStrategy;
	}

	/**
	 * @deprecated Low-level API. Don't use in production code. May be removed
	 * in future versions.
	 */
	@SuppressWarnings({"UnusedDeclaration"})
	@Deprecated
	public Object getItem()
	{
		return item;
	}

	@Override
	public String toHumanReadableString()
	{
		return "[" + Strings.toSingleLineString(item) + "]";
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final CollectionItemElementSelector that = (CollectionItemElementSelector) o;

		if (item != null
				? !identityStrategy.equals(item, that.item)
				: that.item != null)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		return 31;
	}
}
