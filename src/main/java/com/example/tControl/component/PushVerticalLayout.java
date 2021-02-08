package com.example.tControl.component;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.communication.PushMode;


//@Push(PushMode.MANUAL)
@Route("pushVerticalLayout")
public class PushVerticalLayout extends VerticalLayout{

	private FeederThread thread;
	private UI ui;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        //add(new Span("Waiting for updates"));
    	System.out.println("attachEvent");
    	ui = attachEvent.getUI();
    	ui.getPushConfiguration().setPushMode(PushMode.MANUAL);
        // Start the data feed thread
       // thread = new FeederThread(attachEvent.getUI(), this, Component addComponent);
        //thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        //thread.interrupt();
        //thread = null;
    }
    public void addWithPush(Component addComponent) {
    	thread = new FeederThread(ui, this, addComponent);
    	thread.start();
    }

    private static class FeederThread extends Thread {
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
            //try {
//                // Update the data for a while
//                while (count < 10) {
//                    // Sleep to emulate background work
//                    Thread.sleep(500);
//                    String message = "This is update " + count++;
//
//                    ui.access(() -> view.add(new Span(message)));
//                }

                // Inform that we are done
                ui.access(() -> {
                    view.add(addComponent);
                    ui.push();
                });
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
	
}
