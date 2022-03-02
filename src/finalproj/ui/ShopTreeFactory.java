package finalproj.ui;

import javax.swing.tree.DefaultMutableTreeNode;

import finalproj.Barber;
import finalproj.SalonShop;

public class ShopTreeFactory {
	public static DefaultMutableTreeNode createTree(SalonShop shop) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(shop.getName());
		
		if (shop.hasManager()) {
			root.add(new DefaultMutableTreeNode("Manager"));
		}
		
		for (Barber barber : shop.getBarbers()) {
			DefaultMutableTreeNode _barber = new DefaultMutableTreeNode(barber.getName() +", Level: "+String.valueOf(barber.getSkillLevel()));
			root.add(_barber);
		}
		
		return root;
	}
}
