package edu.uptc.run;

import java.io.IOException;
import java.util.ArrayList;

import edu.uptc.gui.MainWindow;
import edu.uptc.logic.Route;
import edu.uptc.logic.Target;
import edu.uptc.persistence.JSonHandler;

public class Main {
	public static void main(String[] args) throws IOException {

		String[] auxNames = JSonHandler.getFileNames("./resources/");
		String[] auxPaths = JSonHandler.getPaths("./resources/");
		
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
		for (String name : auxNames) {
			mw.getDefaultModel().addRow(new Object[] {name, 0});
		}
		fillSources(mw, auxPaths);
		fillTargets(mw, auxPaths);
		
	}

	
	
	private static void fillSources(MainWindow mw, String[] auxPaths) {
		for (String path : auxPaths) {
			ArrayList<Route> aux =  JSonHandler.readJson(path);
			for(Route auxRoute : aux) {
				String auxSource = auxRoute.getSource();
				boolean flagAdded = false;
				if(mw.getCmbSources().getItemCount() == 0) {
					mw.getCmbSources().addItem(auxSource);
					flagAdded = true;
				}else {
					for(int i = 0; i<mw.getCmbSources().getItemCount(); i++) {
						if(mw.getCmbSources().getItemAt(i).equals(auxSource)) {
							flagAdded = true;
						}
					}
				}
				if(flagAdded == false) {
					mw.getCmbSources().addItem(auxSource);
				}
			}
		}		
	}
	
	private static void fillTargets(MainWindow mw, String[] auxPaths) {
		for (String path : auxPaths) {
			ArrayList<Route> aux =  JSonHandler.readJson(path);
			for(Route auxRoute : aux) {
				
				for(Target auxTarget: auxRoute.getTargets()) {
					
					String auxTargetName = auxTarget.getTarget();
					System.out.println(auxTargetName);
					boolean flagAdded = false;
					if(mw.getCmbTargets().getItemCount() == 0) {
						mw.getCmbTargets().addItem(auxTargetName);
						flagAdded = true;
					}else {
						for(int i = 0; i<mw.getCmbTargets().getItemCount(); i++) {
							if(mw.getCmbTargets().getItemAt(i).equals(auxTargetName)) {
								flagAdded = true;
							}
						}
					}
					if(flagAdded == false) {
						mw.getCmbTargets().addItem(auxTargetName);
					}
				}
			}
		}
	}
	
}