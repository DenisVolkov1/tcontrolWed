package com.example.tControl.component;

import java.io.ByteArrayInputStream;

import com.example.tControl.pojo.Employee;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.shared.communication.PushMode;

public class PushImage extends Image {
	private  Employee e;
	private StreamResource imageResource;
	
	 public PushImage(Employee e) {
		 
		 this.e = e;
		 imageResource = new StreamResource("",() -> new ByteArrayInputStream(e.getPhotoByteArray()));
		 imageResource.setCacheTime(0);
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {

		PushImage pi = this;
		UI ui = attachEvent.getUI();
		Thread t = new Thread(new Runnable() {
	
			@Override
			public void run() {
				 ui.access(() -> { 
					 
//		              EntityManagerFactory emf = Persistence.createEntityManagerFactory("my");
//		            	EntityManager em = emf.createEntityManager();
//		            	// Retrieve image from database from user id = 1
//		
//		            	Query q = em.createQuery("SELECT photoByteArray FROM Employee WHERE id = "+idEmployee);
//		            	byte[] bytes = (byte[]) q.getSingleResult();
		            	
		            	//imageResource = new StreamResource("",() -> new ByteArrayInputStream(e.getPhotoByteArray()));
		            	//imageResource.setCacheTime(0);
		            	
		            	pi.getElement().setAttribute("src", imageResource);
	                    ui.getPushConfiguration().setPushMode(PushMode.MANUAL);
	                    ui.push();
		            		
				 });
				
			}
			
		});
		t.start();
	}

	public StreamResource getImageResource() {
		return imageResource;
	}


	
	

}
