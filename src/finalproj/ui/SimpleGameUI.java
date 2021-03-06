package finalproj.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import finalproj.Game;
import finalproj.SalonShop;

public class SimpleGameUI implements ActionListener, GameUI{

	SimpleGameMessageProxy proxy;
	JTree tree;
	JTextField inputField;
	JLabel summary;
	
	public SimpleGameUI() {
		proxy = new SimpleGameMessageProxy();
		tree = new JTree(new DefaultMutableTreeNode("Loading..."));
		inputField = new JTextField();
		summary = new JLabel("<html>Welcome to Salon Shop!<br>Let's get started.</html>");
	}
	
	public void createAndShow() {
		JFrame frame = new JFrame("Salon Shop");
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		//frame.setPreferredSize(new Dimension(500, 250));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.setPreferredSize(new Dimension(350,295));
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		right.setPreferredSize(new Dimension(150, 295));
		
		
		proxy.setBorder(BorderFactory.createEtchedBorder());
		left.add(proxy, BorderLayout.NORTH);
		
		tree.setPreferredSize(new Dimension(150, 175));
		tree.setBorder(BorderFactory.createEtchedBorder());
		right.add(tree, BorderLayout.NORTH);
		
		inputField.addActionListener(this);
		inputField.setBorder(BorderFactory.createLoweredBevelBorder());
		left.add(inputField, BorderLayout.SOUTH);
		
		summary.setPreferredSize(new Dimension(150, 120));
		summary.setBorder(BorderFactory.createEtchedBorder());
		right.add(summary, BorderLayout.SOUTH);
		
		frame.getContentPane().add(left, BorderLayout.WEST);
		frame.getContentPane().add(right, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Game.getInstance().handleCommand(inputField.getText());
		inputField.setText("");
	}
	
	public void setSummary(String javaText) {
		summary.setText("<html>"+javaText.replace("\n", "<br>")+"");
	}
	
	public GameMessageProxy getMessageProxy() {
		return proxy;
	}
	
	public void fullUpdateTree(List<DefaultMutableTreeNode> shops) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Salon Shops");
		for (DefaultMutableTreeNode shop : shops) {
			root.add(shop);
		}
		
		tree.setRootVisible(false);
		tree.setModel(new DefaultTreeModel(root));
	}
	
	public void expandTree(){
		int row = 1;
		while (row++ < tree.getRowCount()){
			tree.expandRow(row);
		}
	}
	
	public void updateState() {
		proxy.tick();
		proxy.doTextUpdate();
		
		if (Game.getInstance().hasTreeUpdate()) {
			List<DefaultMutableTreeNode> shops = new ArrayList<DefaultMutableTreeNode>();
			for (SalonShop salon : Game.getInstance().getShops()) {
				shops.add(ShopTreeFactory.createTree(salon));
			}
			fullUpdateTree(shops);
			expandTree();
			Game.getInstance().setHasTreeUpdate(false);
		}
		setSummary(SimpleGameStatusFactory.getGameStatus(Game.getInstance()));
	}
}
