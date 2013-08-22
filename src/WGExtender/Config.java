/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 */

package WGExtender;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	
	public boolean expandvert = true;
	
	public boolean blocklimitsenabled = true;
	public HashMap<String, Integer> blocklimits = new HashMap<String, Integer>();
	
	public boolean blockliquidflow = true;
	public boolean blocklavaflow = true;
	public boolean blockwaterflow = true;
	public boolean blockigniteotherregionbyplayer = true;
	public boolean blockfirespreadtoregion = true;
	public boolean blockfirespreadinregion = true;
	public boolean blockblockburninregion = true;
	public boolean blockentityexplosionblockdamage = true;
	public boolean blocktntexplosionblockdmage = true;
	public boolean blockcreeperexplosionblockdmage = true;
	
	public void loadConfig()
	{
		loadcfg();
		savecfg();
	}
	
	private void loadcfg()
	{
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/WGExtender/config.yml"));
		
		expandvert = config.getBoolean("vertexpand.enabled",expandvert);
		
		blocklimitsenabled = config.getBoolean("blocklimits.enabled",blocklimitsenabled);
		blocklimits.clear();
		if (config.getConfigurationSection("blocklimits.limits") != null)
		{
			for (String group : config.getConfigurationSection("blocklimits.limits").getKeys(false))
			{
				blocklimits.put(group, config.getInt("blocklimits.limits."+group));
			}
		}
		
		blockliquidflow = config.getBoolean("blockflowtoregion.enabled",blockliquidflow);
		blocklavaflow = config.getBoolean("blockflowtoregion.lava",blocklavaflow);
		blockwaterflow = config.getBoolean("blockflowtoregion.water",blockwaterflow);
		blockigniteotherregionbyplayer = config.getBoolean("blockigniteotherregionbyplayer.enabled",blockigniteotherregionbyplayer);
		blockfirespreadtoregion = config.getBoolean("blockfirespreadtoregion.enabled",blockfirespreadtoregion);
		blockfirespreadinregion = config.getBoolean("blockfirespreadinregion.enabled",blockfirespreadinregion);
		blockblockburninregion = config.getBoolean("blockblockburninregion.enabled",blockblockburninregion);
		blockentityexplosionblockdamage = config.getBoolean("blockentityexplosionblokdamage.enabled",blockentityexplosionblockdamage);
		blocktntexplosionblockdmage = config.getBoolean("blockentityexplosionblokdamage.tnt",blocktntexplosionblockdmage);
		blockcreeperexplosionblockdmage = config.getBoolean("blockentityexplosionblokdamage.creeper",blockcreeperexplosionblockdmage);
		
	}
	
	private void savecfg()
	{
		FileConfiguration config = new YamlConfiguration();
		
		config.set("vertexpand.enabled",expandvert);
		
		config.set("blocklimits.enabled",blocklimitsenabled);
		if (blocklimits.isEmpty())
		{
			config.createSection("blocklimits.limits");
		}
		else
		{
			for (String group : blocklimits.keySet())
			{
				config.set("blocklimits.limits."+group, blocklimits.get(group));
			}
		}
		
		config.set("blockflowtoregion.enabled",blockliquidflow);
		config.set("blockflowtoregion.lava",blocklavaflow);
		config.set("blockflowtoregion.water",blockwaterflow);
		config.set("blockigniteotherregionbyplayer.enabled",blockigniteotherregionbyplayer);
		config.set("blockfirespreadtoregion.enabled",blockfirespreadtoregion);
		config.set("blockfirespreadinregion.enabled",blockfirespreadinregion);
		config.set("blockblockburninregion.enabled",blockblockburninregion);
		config.set("blockentityexplosionblokdamage.enabled",blockentityexplosionblockdamage);
		config.set("blockentityexplosionblokdamage.tnt",blocktntexplosionblockdmage);
		config.set("blockentityexplosionblokdamage.creeper",blockcreeperexplosionblockdmage);
		
		try {
			config.save(new File("plugins/WGExtender/config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
