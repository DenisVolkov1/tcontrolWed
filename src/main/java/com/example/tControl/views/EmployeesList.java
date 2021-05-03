package com.example.tControl.views;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.vaadin.klaudeta.PaginatedGrid;

import com.example.tControl.base.EmployeesListBase;
import com.example.tControl.pojo.Employee;
import com.vaadin.componentfactory.lookupfield.LookupField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

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
	private Button addEmployee;
	private List<Employee> l;
	private Dialog dialog;
	//private byte[] photoByteArray;
	Employee e5 = null;
	private InputStream photoStream;
	
	
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
     
		addEmployee = new Button("Добавить",new Icon(VaadinIcon.PLUS_CIRCLE));
		addEmployee.getStyle().set("margin-left", "30%");
		searchLayout.add(lookupField, searchidCardLayout, addEmployee);
		
		searchedLayout = new HorizontalLayout();
		searchedLayout.setClassName("searchMargin");
		
		TextField searchedPrsonalNumber = new TextField();
			searchedPrsonalNumber.setLabel("Табельный номер");
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
		paginator.setClassName("paginationemployee");
		paginator.setItems(20, 30, 50);
		paginator.setValue(20);
		i1.add(paginator);
		
		/// ADD LISTENERS //////
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
		addEmployee.addClickListener(event -> {
			openDialog();
			dialog.open();
		});
		// END ////////////////////////////////////
	
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
	private void openDialog() {
		dialog = new Dialog();
		FormLayout layoutWithBinder = new FormLayout();
		
		TextField prsonalNumber = new TextField();
		prsonalNumber.setWidth("85%");
		TextField FIO = new TextField();
		FIO.setWidth("85%");
		TextField idCard = new TextField();
		idCard.setWidth("85%");
		TextField division = new TextField();
		division.setWidth("85%");
		TextField position = new TextField();
		position.setWidth("85%");
		Button save = new  Button("Сохранить");
		save.getStyle().set("margin-bottom", "15px");
		layoutWithBinder.addFormItem(prsonalNumber, "Таб. номер");
		layoutWithBinder.addFormItem(FIO, "Ф.И.О.");
		layoutWithBinder.addFormItem(idCard, "ID карты");
		layoutWithBinder.addFormItem(division, "Подразделение");
		layoutWithBinder.addFormItem(position, "Должность");
		//layoutWithBinder.add(photo);

		MemoryBuffer buffer = new MemoryBuffer();
		Upload upload = new Upload(buffer);
		upload.setId("i18n-upload");
		upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");
		// ADD_LISTENERS
		upload.addSucceededListener(event -> {
				photoStream = buffer.getInputStream();
				//photoByteArray = IOUtils.toByteArray(buffer.getInputStream());
		});
		save.addClickListener(event->{
			String inputPrsonalNumber = prsonalNumber.getValue();
			String inputFIO = prsonalNumber.getValue();
			String inputidCard = prsonalNumber.getValue();
			String inputDivision = prsonalNumber.getValue();
			String inputPosition = prsonalNumber.getValue();
			if (inputPrsonalNumber.isEmpty() || inputFIO.isEmpty() || inputidCard.isEmpty() || inputDivision.isEmpty() || inputPosition.isEmpty()) {
				Dialog dialogError = new Dialog();
				dialogError.add(new Text("Заполните все поля!"));
				dialogError.open();
				return;
			}
			
			try {
				EmployeesListBase.insertEmployee(prsonalNumber.getValue(), FIO.getValue(), idCard.getValue(), division.getValue(), position.getValue(), photoStream);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			dialog.close();
		});
		
		// END LISTENERS ///////

		UploadI18N i18n = new UploadI18N();
		i18n.setDropFiles(
		        new UploadI18N.DropFiles().setOne("Перетащите файл сюда...")
		                .setMany("Перетащите файлы сюда..."))
		        .setAddFiles(new UploadI18N.AddFiles()
		                .setOne("Выбрать файл").setMany("Добавить файлы"))
		        .setCancel("Отменить")
		        .setError(new UploadI18N.Error()
		                .setTooManyFiles("Слишком много файлов.")
		                .setFileIsTooBig("Слишком большой файл.")
		                .setIncorrectFileType("Некорректный тип файла."))
		        .setUploading(new UploadI18N.Uploading()
		                .setStatus(new UploadI18N.Uploading.Status()
		                        .setConnecting("Соединение...")
		                        .setStalled("Загрузка застопорилась.")
		                        .setProcessing("Обработка файла..."))
		                .setRemainingTime(
		                        new UploadI18N.Uploading.RemainingTime()
		                                .setPrefix("оставшееся время: ")
		                                .setUnknown(
		                                        "оставшееся время неизвестно"))
		                .setError(new UploadI18N.Uploading.Error()
		                        .setServerUnavailable("Сервер недоступен")
		                        .setUnexpectedServerError(
		                                "Неожиданная ошибка сервера")
		                        .setForbidden("Загрузка запрещена")))
		        .setUnits(Stream
		                .of("Б", "Кбайт", "Мбайт", "Гбайт", "Тбайт", "Пбайт",
		                        "Эбайт", "Збайт", "Ибайт")
		                .collect(Collectors.toList()));

		upload.setI18n(i18n);
		layoutWithBinder.addFormItem(upload, "Фото");

		dialog.setWidth("500px");
		dialog.setHeight("430px");
		dialog.add(layoutWithBinder);
		dialog.add(save);
	}
	private int getINdex(Employee employee) {
		int index = 0;
		List<Employee> items = gridPaginatedEmployees.getDataProvider()
                .fetch(new Query<>())
                .collect(Collectors.toList());
		index = items.indexOf(employee);
		

		return index;
	}
	private Component createComponent(String mimeType, String fileName, InputStream stream) {

        Image image = new Image();
        try {

            byte[] bytes = IOUtils.toByteArray(stream);
            
            image.getElement().setAttribute("src", new StreamResource(fileName, () -> new ByteArrayInputStream(bytes)));
            
            try (ImageInputStream in = ImageIO.createImageInputStream(new ByteArrayInputStream(bytes))) {
            	
                final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
                if (readers.hasNext()) {
                    ImageReader reader = readers.next();
                    try {
                        reader.setInput(in);
                        image.setWidth(reader.getWidth(0) + "px");
                        image.setHeight(reader.getHeight(0) + "px");
                    } finally {
                        reader.dispose();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
	}

	private void showOutput(String text, Component content, HasComponents outputContainer) {
	    HtmlComponent p = new HtmlComponent(Tag.P);
	    p.getElement().setText(text);
	    outputContainer.add(p);
	    outputContainer.add(content);
	}

}
