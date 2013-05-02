package rpg.network.packet;

import rpg.playerinfo.PlayerInformation;
import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketClassNameUpdate extends MinePGPacket {

	private String newClass;

	public PacketClassNameUpdate() { }

	public PacketClassNameUpdate(String newClass) {
		this.newClass = newClass;
	}

	@Override
	protected void writeData(ByteArrayDataOutput out) {
		out.writeUTF(newClass);
	}

	@Override
	protected void readData(ByteArrayDataInput in) {
		newClass = in.readUTF();
	}

	@Override
	protected void execute(EntityPlayer player, Side side) {
		if (side.isClient()) {
			PlayerInformation info = PlayerInformation.forPlayer(player);
			info.setPlayersClass(newClass);
		}
	}

}
