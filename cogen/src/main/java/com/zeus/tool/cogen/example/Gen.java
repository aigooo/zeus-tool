package com.zeus.tool.cogen.example;

import com.zeus.tool.cogen.Cogen;
import com.zeus.tool.cogen.GenUtil;

public class Gen {

    public static void main(String[] args){
    	GenUtil.init("G:\\bigD-workspace\\zeus-tool\\cogen\\");
    	GenUtil.gen4AddPage(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4ListPage(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4EditPage(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4ViewPage(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4RefPage(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4InRefPage(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4Entity(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4Action(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4Service(Cogen.CREATE, Platform.class,Menu.class);
    	GenUtil.gen4HibernateDao(Cogen.CREATE, Platform.class,Menu.class);
    }
}
