# TCPShield
Forge, Fabric, Quilt, etc. mod to block incoming connections to your modded Minecraft server unless they are specifically from TCPShield.

## Features
- **Default Config** similar to the TCPShield plugin's config for Bukkit, Bungee, and Velocity.
- Etc.

## How It Works
When a connection is handled, it parses the connection packet and checks if it originated from TCPShield. If it did, it accepts the connection handshake, if not it blocks it.

## Credits
I did not write most of the code for this, the original plugin devs of the TCPShield plugin (for Bukkit, Bungee, and Velocity) provided most of the code for this. I simply used their own code and adapted it to work with modded Minecraft.

I use my own library (TheBase) to handle the configuration and I use Artifactory to handle multiplatform capabilities.

## Notes
- I did not actually use any code from the upstream repo, and might actually rebase into a standalone repo. I originally was going to use it as a guide, but it turned out that the code simply did not work at all (and was just for Fabric). So, yes, I rewrote the entire thing basically from scratch. As stated above, I used code found in this repo [(**The Official TCPShield Plugin Repo**)](https://github.com/TCPShield/RealIP) and adapted it to be used with Artifactory. I coded all of the Mixin code myself along with the code for the core Artifactory classes.
- I tried to keep the code as clean and slim as possible. If you have suggestions for edits, let me know.
- Also, there is a 1.20.1 version (the main version) and a 1.21.1 version. Note that the 1.21.1 version has not yet been made to work. Only the 1.20.1 version works.

## Support
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
