package org.forgerock.openig.doc;

import java.util.HashMap;
import java.util.Map;

import org.forgerock.openig.alias.ClassAliasResolver;

public class SampleFilterClassAliasResolver implements ClassAliasResolver {
	private static final Map<String, Class<?>> ALIASES = new HashMap<>();

	static {
		ALIASES.put("SampleFilter", SampleFilter.class);
	}

	@Override
	public Class<?> resolve(final String alias) {
		return ALIASES.get(alias);
	}
}
