package com.example.tControl.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.communication.PushMode;


//@Push(PushMode.MANUAL)
@Route("pushVerticalLayout")
public class PushVerticalLayout extends VerticalLayout{

	private FeederThread thread;
	private FeederThreadAddPrevousItems thread2;
	private static UI ui;
	private  List<Component> listItems;
	private boolean isDetached;

    public PushVerticalLayout(List<Component> itemsList) {
    	this.listItems = itemsList;
	}

	@Override
    protected void onAttach(AttachEvent attachEvent) {
        //add(new Span("Waiting for updates"));
    	System.out.println("attachEvent");
    	//isDetached = false;
    	ui =  attachEvent.getUI();
    	thread2 = new FeederThreadAddPrevousItems(ui, this, listItems);
    	thread2.start();
    	

    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
    	System.out.println("DetachEvent");
    	if(thread != null) thread.interrupt();
    	if(thread2 != null) thread2.interrupt();
        thread = null;
        thread2 = null;
    }
    public void addWithPush(Component addComponent) {
    	thread = new FeederThread(ui, this, addComponent);
    	thread.start();
    }

    private class FeederThread extends Thread {
        private final UI ui;
        private final PushVerticalLayout view;
        private final Component addComponent;

        private int count = 0;

        public FeederThread(UI ui, PushVerticalLayout view, Component addComponent) {
            this.ui = ui;
            this.view = view;
            this.addComponent = addComponent;
        }

        @Override
        public void run() {

                ui.access(() -> {
                	listItems.add(addComponent);
                	System.out.println("current size = "+listItems.size());
                    view.add(addComponent);
                    ui.getPushConfiguration().setPushMode(PushMode.MANUAL);
                    ui.push();
                });
        }
    }
	
    
    private class FeederThreadAddPrevousItems extends Thread {
        private final UI ui;
        private final PushVerticalLayout view;
        private List<Component> addedComponents;

        public FeederThreadAddPrevousItems(UI ui, PushVerticalLayout view, List<Component> addedComponents) {
            this.ui = ui;
            this.view = view;
            this.addedComponents = addedComponents;
        }

        @Override
        public void run() {

                ui.access(() -> {
                	synchronized (view) {
                		System.out.println(addedComponents.size());
	                	for(Component c : addedComponents) {
	                			view.add(c);
	                	}
                	}
                    ui.getPushConfiguration().setPushMode(PushMode.MANUAL);
                    ui.push();
                });
        }
    }
}
