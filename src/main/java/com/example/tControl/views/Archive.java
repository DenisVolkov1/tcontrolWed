package com.example.tControl.views;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.swing.SingleSelectionModel;

import org.vaadin.klaudeta.PaginatedGrid;

import com.example.tControl.base.PastEmployeesBase;
import com.example.tControl.component.TimeInputter;
import com.example.tControl.pojo.DataArrayExamples;
import com.example.tControl.pojo.Employee;
import com.example.tControl.pojo.PastEmployees;
import com.vaadin.componentfactory.lookupfield.LookupField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
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
import com.vaadin.flow.component.internal.KeyboardEvent;
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
@CssImport(value="./styles/displayblock.css", themeFor="vaadin-text-field" )
@Tag(value = "d3")

public class Archive extends VerticalLayout {
	private Select<Integer> paginator;
	private PaginatedGrid<PastEmployees> gridPaginatedEmployees;
	private LookupField<PastEmployees> lookupField;
	private List<PastEmployees> l;
	private Button downloadingReport;
	PastEmployees e5 = null;
	//

	TimeInputter timeOt;
	TimeInputter timeDo;
	
	DatePicker datePickerOt;
	DatePicker datePickerDo;
	Button searchDateTimeButton;
	TextField idCardSearch;
	
	public Archive() throws SQLException {
		l = new ArrayList<PastEmployees>(PastEmployeesBase.getAll());

		HorizontalLayout dateTimeSearchLayout = new HorizontalLayout();
		dateTimeSearchLayout.setWidth("450px");
		dateTimeSearchLayout.getStyle().set("margin-left", "15px");
		

		H5 ot = new H5("От");
		ot.setHeight("50px");
		
		VerticalLayout otLayout = new VerticalLayout();
		otLayout.setSpacing(false);
		
		datePickerOt = new DatePicker();
		datePickerOt.getStyle().set("margin-top", "8px");
		datePickerOt.setLocale(new Locale("ru"));
		datePickerOt.setValue(LocalDate.now());
		datePickerOt.setClearButtonVisible(true);
		
		timeOt = new TimeInputter();
		otLayout.add(timeOt, datePickerOt);
		
		H5 doO = new H5("До");
		doO.setHeight("50px");
		
		VerticalLayout doLayout = new VerticalLayout();
		doLayout.setSpacing(false);
		
		datePickerDo = new DatePicker();
		datePickerDo.getStyle().set("margin-top", "8px");
		datePickerDo.setLocale(new Locale("ru"));
		datePickerDo.setValue(LocalDate.now());
		datePickerDo.setClearButtonVisible(true);
		
		timeDo = new TimeInputter();
		doLayout.add(timeDo, datePickerDo);
		
		searchDateTimeButton = new Button(new Icon(VaadinIcon.SEARCH));
		searchDateTimeButton.getStyle().set("margin-top", "18px");
		
		dateTimeSearchLayout.add(ot, otLayout, doO, doLayout, searchDateTimeButton);
		
		HorizontalLayout searchLayout = new HorizontalLayout();
		searchLayout.setClassName("searchMargin");
		
		lookupField = new LookupField<>();
        lookupField.setI18n(new LookupField.LookupFieldI18n()
                .setSelect("Выбрать")
                .setSearch("Поиск")
                .setHeaderpostfix("")
                .setCancel("Отмена"));

		for (Iterator<Component> i = lookupField.getChildren().iterator();i.hasNext();) {
			Component com = i.next();
			if (com.getClass().getName().equals("com.vaadin.flow.component.combobox.ComboBox")) {
				((ComboBox)com).setPlaceholder("ФИО");
			}
			
		}
		
		lookupField.setWidth("390px");
        lookupField.setDataProvider(DataProvider.ofCollection(l));
        lookupField.getGrid().addColumn(s -> s);
        //lookupField.setLabel("Поиск");
       
        
        HorizontalLayout searchidCardLayout = new HorizontalLayout();
        searchidCardLayout.setSpacing(false);
        
        TextField idCardSearch = new TextField();
        idCardSearch.setWidth("300px");
        idCardSearch.setPlaceholder("ID карты");
        
        Button searchIdButton = new Button(new Icon(VaadinIcon.SEARCH));
        searchIdButton.getStyle().set("margin-left", "4px");
        
    
        searchidCardLayout.add(idCardSearch, searchIdButton);
     
		searchLayout.add(lookupField, searchidCardLayout);
		
		gridPaginatedEmployees = new PaginatedGrid<>();
		gridPaginatedEmployees.addColumn(PastEmployees::getWrapperLocalDateTime).setHeader("Дата и время").setSortable(true);
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
		paginatorLayout.setWidthFull();
		//paginatorLayout.getStyle().set("margin-right", "20px");
		//paginatorLayout.setAlignItems(Alignment.START);		
		paginator = new Select<>();

		paginator.setWidth("80px");
		paginator.setClassName("pagination");
		paginator.setItems(20, 30, 50);
		paginator.setValue(20);
		
		downloadingReport = new Button("Скачать отчёт");
		downloadingReport.setClassName("borderButton");
		downloadingReport.setWidth("190px");
		
		paginatorLayout.add(downloadingReport,paginator);
	
		add(dateTimeSearchLayout, searchLayout, gridPaginatedEmployees, paginatorLayout);
		setSpacing(true);
		gridPaginatedEmployees.getDataProvider().withConfigurableFilter();
		
		//ADD-LISTENERS ///////////////////////////////////
		lookupField.addValueChangeListener(event -> {
			List<PastEmployees> l = new ArrayList<PastEmployees>();
			l.add(event.getValue());
			gridPaginatedEmployees.setDataProvider(DataProvider.ofCollection(l));
			gridPaginatedEmployees.getDataProvider().refreshAll();
		});
		paginator.addValueChangeListener(event -> {
			gridPaginatedEmployees.setPageSize(event.getValue());

		});
		searchIdButton.addClickListener(event -> {
			String inputIdCard = idCardSearch.getValue();
			PastEmployees searchedPastEmploee = null;
			for(PastEmployees e : l) {
				if(e.getIdCard().equals(inputIdCard)) {
					searchedPastEmploee = e;
					break;
				}
			}
			List<PastEmployees> l = new ArrayList<PastEmployees>();
			l.add(searchedPastEmploee);
			gridPaginatedEmployees.setDataProvider(DataProvider.ofCollection(l));
			gridPaginatedEmployees.getDataProvider().refreshAll();

		});
		searchDateTimeButton.addClickListener(event -> {
			if(timeOt.isEmptyHours()) timeOt.setHours(0);
			if(timeOt.isEmptyMinutes()) timeOt.setMinutes(0);
			
			if(timeDo.isEmptyHours()) timeDo.setHours(23);
			if(timeDo.isEmptyMinutes()) timeDo.setMinutes(59);
			
			gridPaginatedEmployees.setDataProvider(DataProvider.ofCollection(getFiltredListOnDateTime()));
			gridPaginatedEmployees.getDataProvider().refreshAll();
		});

		
	}
	
	
	private List<PastEmployees> getFiltredListOnDateTime() {
		
		LocalTime localTimeOt = timeOt.getTime();
		LocalTime localTimeDo = timeDo.getTime();
		
		LocalDate localDateOt = datePickerOt.getValue();
		LocalDate localDateDo = datePickerDo.getValue();
		
		LocalDateTime localDateTimeOt = LocalDateTime.of(localDateOt,localTimeOt);
		LocalDateTime localDateTimeDo = LocalDateTime.of(localDateDo,localTimeDo); 
		List<PastEmployees> res = new ArrayList<PastEmployees>();
		
		for(PastEmployees pe : l) {
			if ((pe.getLocalDateTime().isAfter(localDateTimeOt)  || pe.getLocalDateTime().isEqual(localDateTimeOt)) 
			 && (pe.getLocalDateTime().isBefore(localDateTimeDo) || pe.getLocalDateTime().isEqual(localDateTimeDo))) {
				res.add(pe);
			}
		}
		return res;
		
		
	}



}
