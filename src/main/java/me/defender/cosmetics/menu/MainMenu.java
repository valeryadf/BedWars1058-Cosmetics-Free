package me.defender.cosmetics.menu;

import com.cryptomorin.xseries.XMaterial;
import com.hakan.core.HCore;
import com.hakan.core.ui.inventory.InventoryGui;
import me.defender.cosmetics.api.util.MainMenuUtils;
import me.defender.cosmetics.api.util.SkullUtil;
import me.defender.cosmetics.api.util.Utility;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class MainMenu extends InventoryGui {

    FileConfiguration config = Utility.plugin().menuData.getConfig();

    public MainMenu(Player player) {
        super("none", Utility.getMSGLang(player, "cosmetics.gui-title") , 6, InventoryType.CHEST);
    }

    @Override
    public void onOpen(@NotNull Player player) {
        String loc = "Main-Menu";
        String langLoc = "cosmetics.main-menu";
        for(String name : config.getConfigurationSection(loc).getKeys(false)) {
            String material = config.getString(loc + "." + name + ".Material");
            List<String> lore = Utility.getListLang(player, langLoc + "." + name + ".lore");
            String itemName = Utility.getMSGLang(player, langLoc + "." + name + ".name");
            String head = config.getString(loc + "." + name + ".Head-Value");
            int slot = config.getInt(loc + "." + name + ".Slot");
            short data = (short) config.getInt(loc + "." + name + ".Data");
            List<String> lores = MainMenuUtils.formatLore(lore, player);

            if(material != null){
                super.setItem(slot, HCore.itemBuilder(Objects.requireNonNull(XMaterial.matchXMaterial(material).get().parseMaterial())).lores(true, lores).durability(data).name(true, itemName).build(), (e) -> {
                    MainMenuUtils.openMenus((Player) e.getWhoClicked(), name);
                });
            }
            if(head != null){
                super.setItem(slot, HCore.itemBuilder(SkullUtil.makeTextureSkull(head)).name(true, itemName).lores(true, lores).build(), (e) -> {
                    MainMenuUtils.openMenus((Player) e.getWhoClicked(), name);
                });
            }

        }
    }
}
