package com.example.tControl.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SingleSelectionModel;

import org.vaadin.klaudeta.PaginatedGrid;

import com.example.tControl.pojo.DataArrayExamples;
import com.example.tControl.pojo.Employee;
import com.example.tControl.pojo.PastEmployees;
import com.vaadin.componentfactory.lookupfield.LookupField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridSelectionModel;
import com.vaadin.flow.component.grid.GridSingleSelectionModel;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "archive", layout = MainView.class)
@PageTitle("Archive")
@CssImport("./styles/views/archive/archive.css")
@Tag(value = "d3")

public class Archive extends VerticalLayout {
	private Select<Integer> paginator;
	private PaginatedGrid<PastEmployees> gridPaginatedEmployees;
	private List<PastEmployees> l;
	PastEmployees e5 = null;
	//
	TextField timeOt;
	DatePicker datePickerOt;
	TextField timeDo;
	DatePicker datePickerDo;
	TextField idCardSearch;
	
	public Archive() {
		l = new ArrayList<PastEmployees>(DataArrayExamples.getArrayListPastEmployees());

		HorizontalLayout dateTimeSearchLayout = new HorizontalLayout();
		dateTimeSearchLayout.setWidth("450px");
		dateTimeSearchLayout.getStyle().set("margin-left", "15px");
		

		H5 ot = new H5("От");
		ot.setHeight("50px");
		
		VerticalLayout otLayout = new VerticalLayout();
		otLayout.setSpacing(false);
		
		timeOt = new TextField();
		timeOt.setWidth("63px");
		
		timeOt.setValueChangeMode(ValueChangeMode.EAGER);
		timeOt.setPattern("\\d{2}");
	
		timeOt.addValueChangeListener(event -> {
			//Notification.show(event.getValue());
			if(!event.getValue().matches("\\d{2}")) {
				timeOt.setValue(event.getOldValue());
			}

			
		});
		
		datePickerOt = new DatePicker();
		datePickerOt.getStyle().set("margin-top", "8px");
		datePickerOt.setValue(LocalDate.now());
		datePickerOt.setClearButtonVisible(true);
		otLayout.add(timeOt, datePickerOt);
		
		H5 doO = new H5("До");
		doO.setHeight("50px");
		
		VerticalLayout doLayout = new VerticalLayout();
		doLayout.setSpacing(false);
		timeDo = new TextField();
		timeDo.setWidth("63px");
		datePickerDo = new DatePicker();
		datePickerDo.getStyle().set("margin-top", "8px");
		datePickerDo.setValue(LocalDate.now());
		datePickerDo.setClearButtonVisible(true);
		doLayout.add(timeDo, datePickerDo);
		
		Button searchDateTimeButton = new Button(new Icon(VaadinIcon.SEARCH));
		searchDateTimeButton.getStyle().set("margin-top", "18px");
		
		dateTimeSearchLayout.add(ot, otLayout, doO, doLayout, searchDateTimeButton);
		
		HorizontalLayout searchLayout = new HorizontalLayout();
		searchLayout.setClassName("searchMargin");
		
		LookupField<PastEmployees> lookupField = new LookupField<>();

		for (Iterator<Component> i = lookupField.getChildren().iterator();i.hasNext();) {
			Component com = i.next();
			if (com.getClass().getName().equals("com.vaadin.flow.component.combobox.ComboBox")) {
				((ComboBox)com).setPlaceholder("ФИО");
			}
			
		}
		
		lookupField.setWidth("390px");
        lookupField.setDataProvider(DataProvider.ofCollection(l));
        lookupField.getGrid().addColumn(s -> s).setHeader("item");
        
        HorizontalLayout searchidCardLayout = new HorizontalLayout();
        searchidCardLayout.setSpacing(false);
        //searchidCardLayout.getStyle().set("margin-top", "4px");
        
        TextField idCardSearch = new TextField();
        idCardSearch.setWidth("300px");
        idCardSearch.setPlaceholder("ID карты");
        
        Button searchIdButton = new Button(new Icon(VaadinIcon.SEARCH));
        searchIdButton.getStyle().set("margin-left", "4px");
        
    
        searchidCardLayout.add(idCardSearch, searchIdButton);
     
		searchLayout.add(lookupField, searchidCardLayout);
		
//		HorizontalLayout searchedLayout = new HorizontalLayout();
//		searchedLayout.setClassName("searchMargin");
		
//		TextField searchedPrsonalNumber = new TextField();
//			searchedPrsonalNumber.setLabel("№");
//			searchedPrsonalNumber.setWidth("6%");
//		TextField searchedFIO = new TextField();
//			searchedFIO.setLabel("Ф.И.О.");
//			searchedFIO.setWidth("20%");
//		TextField searchedIdCard = new TextField();
//			searchedIdCard.setLabel("ID карты");
//			searchedIdCard.setWidth("10%");
//		TextField searchedDivision = new TextField();
//			searchedDivision.setLabel("Подразделение");
//			searchedDivision.setWidth("10%");
//		TextField searchedPosition = new TextField();
//			searchedPosition.setLabel("Должность");
//			searchedPosition.setWidth("10%");
//		Button rollUpSearch = new Button(new Icon(VaadinIcon.ARROW_UP));
//		rollUpSearch.getStyle().set("margin-top", "36px");
	
		//searchedLayout.add(searchedPrsonalNumber, searchedFIO, searchedIdCard, searchedDivision, searchedPosition, rollUpSearch);
		//searchedLayout.setVisible(false);
		//searchedLayout.setEnabled(false);
		
		//rollUpSearch.addClickListener(event -> {
//			searchedLayout.setEnabled(false);
//			searchedLayout.setVisible(false);
//				searchLayout.setEnabled(true);
//				searchLayout.setVisible(true);
//		});
		
		
		gridPaginatedEmployees = new PaginatedGrid<>();
		gridPaginatedEmployees.addColumn(PastEmployees::getDateTimePass).setHeader("Дата и время").setSortable(true);
		gridPaginatedEmployees.addColumn(PastEmployees::getFio)			  .setHeader("Фамилия Имя Отчество").setSortable(true);
		gridPaginatedEmployees.addColumn(PastEmployees::getIdCard)  		  .setHeader("ID Карты").setSortable(true);
		gridPaginatedEmployees.addColumn(PastEmployees::getTC)       .setHeader("Подразделение").setSortable(true);
		gridPaginatedEmployees.addColumn(PastEmployees::getPassage)       .setHeader("Должность").setSortable(true);
		gridPaginatedEmployees.setSizeFull();
		gridPaginatedEmployees.setHeight("60%");
		gridPaginatedEmployees.setDataProvider(DataProvider.ofCollection(l));
		gridPaginatedEmployees.setPageSize(20);
		gridPaginatedEmployees.setSelectionMode(SelectionMode.SINGLE); 

		gridPaginatedEmployees.setPaginatorSize(5);

		searchIdButton.addClickListener(event -> {
	

			
		});
		
		HorizontalLayout paginatorLayout = new HorizontalLayout();
		paginatorLayout.getStyle().set("margin-right", "20px");
		paginatorLayout.setAlignItems(Alignment.START);		
		paginator = new Select<>();
		paginator.addValueChangeListener(event -> {
			gridPaginatedEmployees.setPageSize(event.getValue());

		});
		paginator.setWidth("80px");
		paginator.setClassName("pagination");
		paginator.setItems(20, 30, 50);
		paginator.setValue(20);
		paginatorLayout.add(paginator);
	
		add(dateTimeSearchLayout, searchLayout, gridPaginatedEmployees, paginatorLayout);
		setSpacing(true);
		gridPaginatedEmployees.getDataProvider().withConfigurableFilter();
		
	}
	private void selectRow(Employee employee) {

	
//		int totalPages = 0;
//		if (l.size() > 0) {
//			totalPages = (int) Math.ceil((float)l.size() / paginator.getValue()); 
//		} 
		int indexOnPage = getINdex(employee); 
		System.out.println(indexOnPage);
		int numberPages = 0;
		if (indexOnPage > 0) {
			numberPages = (int) Math.ceil((float) indexOnPage / paginator.getValue()); 
				//System.out.println("countPages"+numberPages);
		}
		gridPaginatedEmployees.setPage(numberPages);
		//m.select(e5);
		gridPaginatedEmployees.scrollToIndex(19);
		//gridPaginatedEmployees.getDataProvider().refreshAll();
		//gridPaginatedEmployees.refreshPaginator();
		gridPaginatedEmployees.getSelectionModel().select(e5);
		
	//m.select(e5);
		
		
	
		
		
		//System.out.println(getINdex(employee)%paginator.getValue());
		
//		for (int i = 1; totalPages >= i; i++) {
//			gridPaginatedEmployees.setPage(i);
//			gridPaginatedEmployees.setPageSize(l.size());
//
//			
//			ListDataProvider listDataProvider = (ListDataProvider) gridPaginatedEmployees.getDataProvider();
//			ArrayList items = (new ArrayList(listDataProvider.getItems()));
//
//			int index = items.indexOf(e5);
//			System.out.println(index);
//			System.out.println(gridPaginatedEmployees.getPage());
//			if (index != -1) {
//				System.out.println(index);
//
//				break;
//			}
//		}


		
	}
	private int getINdex(Employee employee) {
		int index = 0;
		//index = l.indexOf(employee);
		
		
//		gridPaginatedEmployees.setPageSize(l.size());
//		ListDataProvider listDataProvider = (ListDataProvider) gridPaginatedEmployees.getDataProvider();
//		ArrayList items = (new ArrayList(listDataProvider.getItems()));
//		index = items.indexOf(employee);
//		gridPaginatedEmployees.setPageSize(paginator.getValue());
		return index;
	}

}
