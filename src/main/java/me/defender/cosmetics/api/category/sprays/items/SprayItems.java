package me.defender.cosmetics.api.category.sprays.items;

import me.defender.cosmetics.api.category.sprays.Spray;
import me.defender.cosmetics.api.category.sprays.util.SpraysUtil;
import me.defender.cosmetics.api.enums.CosmeticsType;
import me.defender.cosmetics.api.enums.RarityType;
import me.defender.cosmetics.api.util.StringUtils;
import me.defender.cosmetics.api.configuration.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SprayItems {

    public static void registerConfigItems(){
        ConfigurationSection section = CosmeticsType.Sprays.getConfig().getYml().getConfigurationSection(CosmeticsType.Sprays.getSectionKey());
        if(section == null) return;
        ConfigManager config = CosmeticsType.Sprays.getConfig();
        for(String id : section.getKeys(false)) {
            String path = CosmeticsType.Sprays.getSectionKey() + "." + id + ".";
            Spray spray = new Spray() {
                @Override
                public ItemStack getItem() {
                    return config.getItemStack(path + "item");
                }

                @Override
                public String base64() {
                    return null;
                }

                @Override
                public String getIdentifier() {
                    return id;
                }

                @Override
                public String getDisplayName() {
                    return StringUtils.replaceHyphensAndCaptalizeFirstLetter(id);
                }

                @Override
                public List<String> getLore() {
                    if(getRarity() == RarityType.NONE){
                        return List.of("&7Selecting this option disables your", "&7Spray.");
                    }
                    return List.of("&7Select " + getDisplayName() + " as your", "&7Spray!");
                }

                @Override
                public int getPrice() {
                    return config.getInt(path + "price");
                }

                @Override
                public RarityType getRarity() {
                    return RarityType.valueOf(config.getString(path + "rarity").toUpperCase());
                }

                @Override
                public void execute(Player player, ItemFrame frame) {
                    SpraysUtil.spawnSprays(player, frame);
                }
            };
            spray.register();
        }
    }
}
