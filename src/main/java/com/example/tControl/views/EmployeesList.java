package com.example.tControl.views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SingleSelectionModel;

import org.vaadin.klaudeta.PaginatedGrid;

import com.example.tControl.base.ConnectionPool;
import com.example.tControl.base.EmployeesListBase;
import com.example.tControl.pojo.DataArrayExamples;
import com.example.tControl.pojo.Employee;
import com.vaadin.componentfactory.lookupfield.LookupField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridSelectionModel;
import com.vaadin.flow.component.grid.GridSingleSelectionModel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "employeesList", layout = MainView.class)
@PageTitle("Employees List")
@CssImport("./styles/views/employees/employees.css")
@Tag(value = "d2")
public class EmployeesList extends VerticalLayout {
	private Select<Integer> paginator;
	private PaginatedGrid<Employee> gridPaginatedEmployees;
	private LookupField<Employee> lookupField;
	private HorizontalLayout searchLayout;
	private HorizontalLayout searchedLayout;
	private TextField idCardSearch;
	private List<Employee> l;
	Employee e5 = null;
	
	
	public EmployeesList() throws SQLException {
		l = new ArrayList<Employee>(EmployeesListBase.getAll());
		
		searchLayout = new HorizontalLayout();
		searchLayout.setClassName("searchMargin");
		
		lookupField = new LookupField<>();
        lookupField.setI18n(new LookupField.LookupFieldI18n()
                .setSelect("Выбрать")
                .setSearch("Поиск")
                .setHeaderpostfix("")
                .setCancel("Отмена"));
        
		for (Iterator<Component> i1 = lookupField.getChildren().iterator();i1.hasNext();) {
			Component com = i1.next();
			if (com.getClass().getName().equals("com.vaadin.flow.component.combobox.ComboBox")) {
				((ComboBox)com).setPlaceholder("ФИО");
			}
		}
		
		lookupField.setWidth("390px");
        lookupField.setDataProvider(DataProvider.ofCollection(l));
        lookupField.getGrid().addColumn(s -> s);
        lookupField.setLabel("Поиск");
        
        
        HorizontalLayout searchidCardLayout = new HorizontalLayout();
        searchidCardLayout.setSpacing(false);
        searchidCardLayout.getStyle().set("margin-top", "34px");
        
        idCardSearch = new TextField();
        idCardSearch.setWidth("300px");
        idCardSearch.setPlaceholder("ID карты");
        
        Button searchIdButton = new Button(new Icon(VaadinIcon.SEARCH));
        searchIdButton.getStyle().set("margin-left", "4px");
        
    
        searchidCardLayout.add(idCardSearch, searchIdButton);
     
		
		searchLayout.add(lookupField, searchidCardLayout);
		
		searchedLayout = new HorizontalLayout();
		searchedLayout.setClassName("searchMargin");
		
		TextField searchedPrsonalNumber = new TextField();
			searchedPrsonalNumber.setLabel("№");
			searchedPrsonalNumber.setWidth("6%");
		TextField searchedFIO = new TextField();
			searchedFIO.setLabel("Ф.И.О.");
			searchedFIO.setWidth("25%");
		TextField searchedIdCard = new TextField();
			searchedIdCard.setLabel("ID карты");
			searchedIdCard.setWidth("10%");
		TextField searchedDivision = new TextField();
			searchedDivision.setLabel("Подразделение");
			searchedDivision.setWidth("25%");
		TextField searchedPosition = new TextField();
			searchedPosition.setLabel("Должность");
			searchedPosition.setWidth("25%");
		Button rollUpSearch = new Button(new Icon(VaadinIcon.ARROW_UP));
		rollUpSearch.getStyle().set("margin-top", "36px");
	
		searchedLayout.add(searchedPrsonalNumber, searchedFIO, searchedIdCard, searchedDivision, searchedPosition, rollUpSearch);
		searchedLayout.setVisible(false);
		searchedLayout.setEnabled(false);
		
		gridPaginatedEmployees = new PaginatedGrid<>();
		gridPaginatedEmployees.addColumn(Employee::getPersonnelNumber).setHeader("Personnel Number").setSortable(true);
		gridPaginatedEmployees.addColumn(Employee::getFio)			  .setHeader("Fio").setSortable(true);
		gridPaginatedEmployees.addColumn(Employee::getIdCard)  		  .setHeader("ID Card").setSortable(true);
		gridPaginatedEmployees.addColumn(Employee::getDivision)       .setHeader("Division").setSortable(true);
		gridPaginatedEmployees.addColumn(Employee::getPosition)       .setHeader("Position").setSortable(true);
		gridPaginatedEmployees.setSizeFull();
		gridPaginatedEmployees.setHeight("74%");
		gridPaginatedEmployees.setDataProvider(DataProvider.ofCollection(l));
		// Sets the max number of items to be rendered on the grid for each page
		gridPaginatedEmployees.setPageSize(20);
		gridPaginatedEmployees.setSelectionMode(SelectionMode.SINGLE); 
		//m  =  gridPaginatedEmployees.getSelectionModel();
		// Sets how many pages should be visible on the pagination before and/or after the current selected page
		gridPaginatedEmployees.setPaginatorSize(5);
		//l2.add(gridPaginatedEmployees);
		//l2.getStyle().set("overflow", "auto");
		//l2.setHeight("650px");
		//gridPaginatedEmployees.scrollToIndex(50);

		
		HorizontalLayout i1 = new HorizontalLayout();
		i1.getStyle().set("margin-right", "20px");
		i1.setAlignItems(Alignment.START);		
		paginator = new Select<>();
		paginator.setWidth("80px");
		paginator.setClassName("pagination");
		paginator.setItems(20, 30, 50);
		paginator.setValue(20);
		i1.add(paginator);
		/// ADD LISTENERS
		lookupField.addValueChangeListener(event -> {
			//System.out.println("cllick");
			if (event.getValue() != null) {
				hideLookupFields();
					Employee e = event.getValue();
				 searchedPrsonalNumber.setValue(e.getPersonnelNumber());
				 searchedFIO.setValue(e.getFio());
				 searchedIdCard.setValue(e.getIdCard());
				 searchedDivision.setValue(e.getDivision());
				 searchedPosition.setValue(e.getPosition());

				
			}
		});
		rollUpSearch.addClickListener(event -> {
			showLookupFields();
		});		
		searchIdButton.addClickListener(event -> {
			String inputIdCard = idCardSearch.getValue();
			Employee searchedEmploee = null;
			hideLookupFields();
			for(Employee e : l) {
				if(e.getIdCard().equals(inputIdCard)) {
					searchedEmploee = e;
					break;
				}
			}
		 if(searchedEmploee != null) {
			 searchedPrsonalNumber.setValue(searchedEmploee.getPersonnelNumber());
			 searchedFIO.setValue(searchedEmploee.getFio());
			 searchedIdCard.setValue(searchedEmploee.getIdCard());
			 searchedDivision.setValue(searchedEmploee.getDivision());
			 searchedPosition.setValue(searchedEmploee.getPosition());
		 }
				
			
		});
		paginator.addValueChangeListener(event -> {
			gridPaginatedEmployees.setPageSize(event.getValue());

		});
		
	
		add(searchLayout, searchedLayout, gridPaginatedEmployees, i1);
		
	}
	private void hideLookupFields() {
		searchLayout.setEnabled(false);
		searchLayout.setVisible(false);
			searchedLayout.setEnabled(true);
			searchedLayout.setVisible(true);
	}
	private void showLookupFields() {
		searchLayout.setEnabled(true);
		searchLayout.setVisible(true);
			searchedLayout.setEnabled(false);
			searchedLayout.setVisible(false);
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
		


		
	}
	private int getINdex(Employee employee) {
		int index = 0;
		//index = l.indexOf(employee);
		List<Employee> items = gridPaginatedEmployees.getDataProvider()
                .fetch(new Query<>())
                .collect(Collectors.toList());
		index = items.indexOf(employee);
		

		return index;
	}

}
