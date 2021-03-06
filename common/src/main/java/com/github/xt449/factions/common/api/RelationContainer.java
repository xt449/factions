/*
 * Copyright (c) 2022 Jonathan Talcott (xt449 / BinaryBanana)
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
 *
 */

package com.github.xt449.factions.common.api;

import org.jetbrains.annotations.Nullable;

/**
 * Represents anything that has the capability of being (in) a faction
 */
public interface RelationContainer {

	/**
	 * @return null if no relation set or relation not applicatable
	 */
	@Nullable
	FactionRelation getRelationTo(RelationContainer other);

	/**
	 * @return null if not part of a Faction
	 */
	@Nullable
	IFaction getFaction();
}
