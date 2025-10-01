# TCPShield
Forge, Fabric, Quilt, etc. mod to block incoming connections to your Minecraft server unless they are specifically from TCPShield.

### Features
- **Default Config** similar to the TCPShield plugins for Bukkit, Bungee, and Velocity.
- Etc.

### How It Works
When a connection is handled, it parses the connection packet and checks if it originated from TCPShield. If it did, it accepts the connection handshake, if not it blocks it.

### Credits
I did not write most of the code for this, the original plugin devs of the TCPShield plugin (for Bukkit, Bungee, and Velocity) provided most of the code for this. I simply used their own code and adapted it to work with modded Minecraft.

I use my own library (TheBase) to handle the configuration and I use Artifactory to handle multiplatform capabilities.

### Support
*If you have any issues with the mod*

You **will not**:
- Make a ticket in the TCPShield discord asking for help.
- Make an issue on the main TCPShield plugin GitHub repo.
- Harass anyone other than me (Drak - @drakdv on Discord) about fixing it.

You **will**:
- Join my support Discord for help or bug reports: https://dsc.gg/streamline
- Only ask for help from Drak (or contributing devs).
- Understand that I am not at all associated with TCPShield or the TCPShield brand.

Thank you.
