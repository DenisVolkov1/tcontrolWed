package com.example.tControl.views;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Bottom;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@Route(value = "desktop", layout = MainView.class)
@PageTitle("Desktop")
@CssImport("./styles/views/desktop/desktop.css")
@RouteAlias(value = "", layout = MainView.class)
@Tag(value = "d1")
public class Desktop extends VerticalLayout {

	
	public Desktop() {
		
		HorizontalLayout extendedInformation = new HorizontalLayout();
		//extendedInformation.getStyle().set("display","block");
		//extendedInformation.setSizeUndefined();

		VerticalLayout labelExtendedInformation = new VerticalLayout();
		H6 fioExtendedInformation = new H6("Фролов Максим Викторович");
		H6 divisionExtendedInformation = new H6("Отдел продаж");
		H6 positionExtendedInformation = new H6("Бухгалтер");
		H6 idCardExtendedInformation = new H6("132456258");
		H6 tCExtendedInformation = new H6("37.8");
		H6 timeExtendedInformation = new H6("20:30");
		labelExtendedInformation.add(fioExtendedInformation, divisionExtendedInformation,positionExtendedInformation ,idCardExtendedInformation,tCExtendedInformation,timeExtendedInformation);
		
		Image photoExtendedInformation = new Image("images/noPhoto.png", "noPhoto");
		photoExtendedInformation.setWidth("400px");
		photoExtendedInformation.setHeight("400px");
		Button buttonRollUp = new Button(new Icon(VaadinIcon.ARROW_UP), event -> {
			extendedInformation.setEnabled(false);
			extendedInformation.setVisible(false);
			
		});
		buttonRollUp.setMaxWidth("90px");
		
		
		extendedInformation.add(labelExtendedInformation, photoExtendedInformation,buttonRollUp);
		
		HorizontalLayout listBoxLayout = new HorizontalLayout();
		
		ListBox listBox = new ListBox<>();
		listBox.addValueChangeListener(event -> {
			extendedInformation.setEnabled(true);
			extendedInformation.setVisible(true);
		});

		listBoxLayout.getStyle().set("overflow", "auto");
		//listBoxLayout.getStyle().set("border", "1px solid");
		//listBoxLayout.setWidth("1300px");
		listBoxLayout.setHeight("500px");

		
		List l = new ArrayList();
		for (int i = 0; i < 70; i++) {
			HorizontalLayout layout = new HorizontalLayout();
			
			
			Image image = new Image("images/noPhoto.png", "noPhoto");
			image.setWidth("90px");
			image.setHeight("90px");
			layout.add(image);
			VerticalLayout layout2 = new VerticalLayout();

			H5 fio = new H5("Фролов Максим Викторович");
			fio.setClassName("h5");

			H6 cardId = new H6("456789258");
			//cardId.setClassName("h5");

			layout2.add(fio, cardId);
			
			VerticalLayout layout3 = new VerticalLayout();
			H6 tC = new H6("37.7");
			tC.setClassName("h5");

			H6 time = new H6("20:45");
			//time.setClassName("h5");

			layout3.add(tC, time);




			listBox.setItems();
			layout.add(image,layout2,layout3);
			l.add(layout);
		}
		
		listBox.setRenderer(new ComponentRenderer(item -> {

		    return item;
		}));
		listBox.setItems(l);
		listBox.setSizeFull();
		listBoxLayout.add(listBox);
		
		//setWidth("500px");
		//setMaxHeight("1500px");
		add(extendedInformation,listBoxLayout);

		
	}
	
	
}
