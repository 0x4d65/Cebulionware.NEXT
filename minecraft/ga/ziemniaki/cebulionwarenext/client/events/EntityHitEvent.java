package ga.ziemniaki.cebulionwarenext.client.events;

import net.minecraft.entity.Entity;

public class EntityHitEvent extends Event {

	public Entity entity;

	public EntityHitEvent(Entity en) {
		this.entity = en;
	}
}
