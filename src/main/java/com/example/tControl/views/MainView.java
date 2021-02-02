package com.example.tControl.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@CssImport("./styles/views/main/main-view.css")
@PWA(name = "My Project", shortName = "My Project", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")

public class MainView extends AppLayout {

	private final Tabs menu;
    private H1 viewTitle;
    private H1 time;
    private H1 date;
    private TimeAndDateDeamonThread andDateDeamonThread;

    public MainView() {
        setPrimarySection(Section.NAVBAR);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }
    private class TimeAndDateDeamonThread extends Thread {
    	
    	public TimeAndDateDeamonThread() {
    		
    		setDaemon(true);
    	}
    	public void run() {
            while (true) {

                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");

                time.setText(timeFormat.format( System.currentTimeMillis()));
                date.setText(dateFormat.format( System.currentTimeMillis()));
                System.out.println("time "+timeFormat.format(System.currentTimeMillis())+"date "+dateFormat.format(System.currentTimeMillis()));
                
                try {sleep(500);} catch (InterruptedException e) {}
                                    	
            }
        }
    	
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
       // HorizontalLayout layout2 = new HorizontalLayout();
       // layout2.setAlignItems(Alignment.END);
//        date = new H1("21.01.2021");
//        date.getStyle().set("margin-left", "auto");
//        date.getStyle().set("margin-right", "20px");
//        time = new H1("20:29");
//        andDateDeamonThread = new TimeAndDateDeamonThread();
//        andDateDeamonThread.start();
       // time.getStyle().set("margin-left", "auto");
        //time.getStyle().set("margin-right", "20px");
      //  layout2.add(time,date);
        layout.add(viewTitle);
        //layout.add(new Avatar());
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "My Project logo"));
        logoLayout.add(new H1("My Project"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        return new Tab[]{createTab("Desktop", Desktop.class), createTab("Employees List", EmployeesList.class)};
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }
	
	
}
