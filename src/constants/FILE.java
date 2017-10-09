package constants;

public interface FILE {
	
	String HOME_DIR = System.getProperty("user.home");
	
	String FOLDER_DIR=HOME_DIR+"/.ChangerThemeGTK/dir";
	String FOLDER_LIST=HOME_DIR+"/.ChangerThemeGTK/list";
	
	
	String LIST_SOFT=FOLDER_LIST+"/list_soft.log";
	String LIST_SOFT_DIR=FOLDER_DIR+"/list_soft_dir.log";
	
	String LIST_THEMES=FOLDER_LIST+"/list_themes.log";
	String LIST_THEMES_DIR=FOLDER_DIR+"/list_themes_dir.log";
	
	String LIST_DESKTOP=FOLDER_LIST+"/list_desktop.log";
	
	
	String HOME_APPLICATIONS=HOME_DIR+"/.local/share/applications/";
}
