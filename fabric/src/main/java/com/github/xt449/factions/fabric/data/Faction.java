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

package com.github.xt449.factions.fabric.data;

import com.github.xt449.factions.common.api.*;
import com.github.xt449.factions.common.data.ChunkPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author Jonathan Talcott (xt449 / BinaryBanana)
 */
public class Faction implements IFaction {

	public final HashMap<UUID, PlayerData> player2Data = new HashMap<>();
	public final HashMap<Faction, FactionRelation> faction2Relation = new HashMap<>();
	public final HashSet<ChunkPosition> claims = new HashSet<>();

	private int id;
	private String name;
	private PlayerData leaderPlayerData;

	public Faction(int id, String name, PlayerData leaderPlayerData) {
		this.id = id;
		this.name = name;
		this.leaderPlayerData = leaderPlayerData;

		leaderPlayerData.faction = this;
		leaderPlayerData.role = FactionRole.LEADER;
		player2Data.put(leaderPlayerData.getId(), leaderPlayerData);
	}

	public Faction(String name, PlayerData leaderPlayerData) {
		this.name = name;
		this.leaderPlayerData = leaderPlayerData;

		leaderPlayerData.faction = this;
		leaderPlayerData.role = FactionRole.LEADER;
		player2Data.put(leaderPlayerData.getId(), leaderPlayerData);
	}

	/**
	 * Should be only used internally
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Use {@link String#toLowerCase()} for commands and {@link IRegistrar#getFaction(String)}
	 */
	@Override
	public @NotNull String getName() {
		return name;
	}

	/**
	 * @return null if Wilderness
	 */
	@Override
	public @Nullable IPlayerData getLeader() {
		return leaderPlayerData;
	}

	/**
	 * @return chunk positions of all current faction claims (Immutable)
	 */
	@Override
	public @NotNull Set<ChunkPosition> getClaims() {
		return Collections.unmodifiableSet(claims);
	}

	/**
	 * Includes ALL players, including: bans, kicks, invites, etc. (Immutable)
	 */
	@Override
	public @NotNull Map<UUID, IPlayerData> getAllPlayers() {
		return Collections.unmodifiableMap(player2Data);
	}

	/**
	 * @return null if no relation set or relation not applicatable
	 */
	@Override
	public @Nullable FactionRelation getRelationTo(RelationContainer other) {
		return faction2Relation.get(other.getFaction());
	}
}
