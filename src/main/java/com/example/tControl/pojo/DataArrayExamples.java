package com.example.tControl.pojo;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DataArrayExamples {

	static String[] fio = new String[] {
			"Абрамов Даниил Матвеевич",
			"Агафонов Ярослав Фёдорович",
			"Акимова Ангелина Ильинична",
			"Андреев Марк Владимирович",
			"Андреев Алексей Максимович",
			"Андреева Алина Максимовна",
			"Андрианов Даниил Никитич",
			"Анисимова Анна Тимофеевна",
			"Антонова Василиса Гордеевна",
			"Артемова Алина Лукинична",
			"Афанасьев Ярослав Романович",
			"Баранов Арсений Лукич",
			"Белов Алексей Романович",
			"Борисов Леонид Тимофеевич",
			"Виноградов Иван Даниилович",
			"Виноградова Полина Степановна",
			"Владимиров Артём Ильич",
			"Волков Дмитрий Фёдорович",
			"Голубева Анна Игоревна",
			"Горюнова Анастасия Евгеньевна",
			"Гусева Екатерина Вячеславовна",
			"Дегтярева Ольга Робертовна",
			"Демин Фёдор Даниилович",
			"Денисов Тимофей Михайлович",
			"Дмитриев Мирон Даниилович",
			"Добрынин Никита Никитич",
			"Дроздова Анна Никитична",
			"Еремин Артём Владиславович",
			"Ефремов Александр Артёмович",
			"Жукова Алиса Родионовна",
			"Журавлева Дарья Андреевна",
			"Захарова Варвара Тимуровна",
			"Иванов Дмитрий Артёмович",
			"Иванова Арина Матвеевна",
			"Иванова Полина Данииловна",
			"Исаев Александр Тимофеевич",
			"Киселева Евгения Артёмовна",
			"Киселева Анастасия Романовна",
			"Козлов Сергей Иванович",
			"Козлов Денис Александрович",
			"Козлова Василиса Леонидовна",
			"Козлова Вероника Ярославовна",
			"Кондрашов Сергей Денисович",
			"Копылова Ксения Борисовна",
			"Костина Елизавета Тимофеевна",
			"Котова Юлия Романовна",
			"Крылова Ульяна Матвеевна",
			"Кудрявцева Татьяна Тимофеевна",
			"Кузина Мария Ивановна",
			"Кузнецова Полина Александровна",
			"Куликов Арсений Макарович",
			"Аазарев Иван Матвеевич",
			"Логинов Тимур Фёдорович",
			"Лукьянова Яна Викторовна",
			"Макарова Алиса Ивановна",
			"Максимов Дмитрий Егорович",
			"Маркина Василиса Никитична",
			"Медведева Мария Григорьевна",
			"Мещерякова Екатерина Ивановна",
			"Михайлова Елизавета Никитична",
			"Михеева София Тимуровна",
			"Моисеев Артём Ильич",
			"Морозов Богдан Ильич",
			"Назарова Анна Александровна",
			"Никитин Иван Максимович",
			"Никифорова Виктория Ивановна",
			"Николаева Василиса Андреевна",
			"Николаева Светлана Борисовна",
			"Никулина Полина Матвеевна",
			"Олейников Константин Степанович",
			"Павлов Вадим Захарович",
			"Панкратов Марк Артёмович",
			"Поздняков Ярослав Михайлович",
			"Румянцев Вячеслав Алексеевич",
			"Рыбаков Сергей Дмитриевич",
			"Рябинина Марина Яновна",
			"Семенова Кира Андреевна",
			"Сергеев Роман Дмитриевич",
			"Смирнов Андрей Ильич",
			"Смирнов Артём Алексеевич",
			"Смирнова София Ивановна",
			"Снегирева Полина Марковна",
			"Соколова Алина Тимофеевна",
			"Спиридонова Полина Руслановна",
			"Степанов Игорь Даниилович",
			"Сычев Олег Константинович",
			"Тарасова Маргарита Александровна",
			"Тимофеев Матвей Святославович",
			"Титов Даниил Денисович",
			"Титов Алексей Михайлович",
			"Титова Алёна Ивановна",
			"Трифонов Михаил Филиппович",
			"Усов Дмитрий Иванович",
			"Федоров Александр Леонидович",
			"Федоров Марк Степанович",
			"Федотов Фёдор Егорович",
			"Хохлова Екатерина Константиновна",
			"Хохлова Алина Вячеславовна",
			"Швецов Егор Васильевич",
			"Яковлева Ксения Дмитриевна"
			};

	static String[] position = new String[] {"Квалифицированный рабочий",
	"Менеджер по продажам",
	"Начальник отдела продаж",
	"Инженер",
	"Продавец",
	"Водитель",
	"Неквалифицированный рабочий",
	"Бухгалтер",
	"Врач",
	"Региональный представитель",
	"Художник-иллюстратор",
	"Художественный редактор",
	"Авиационный инженер",
	"UX-дизайнер"	,
		"Географ",	
"Пластический хирург",	
	"Прокурор",
		"Биофизик",	
	"Звукорежиссер"	,
	"Художник",
	"Градостроитель"};
	
	static String[] division = new String[] {
	"Отдел продаж (отдел сбыта)",
	"Отдел снабжения",
	"Бухгалтерия",
	"Отдел технической поддержки",
	"IT отдел",
	"Юридический отдел",
	"Отдел маркетинга",
	"Отдел кадров"
	};
	static String[] passage = new String[] {
			"Проходная №1",
			"Проходная №2",
			"Проходная №3",
			"Проходная №4",
			"Проходная №5",
			"Проходная №6",
			"Проходная №7",
			"Проходная №8",
			"Проходная №9",
			"Проходная №10",
			"Проходная №11",
			"Проходная №12",
			"Проходная №13",
			"Проходная №14",
			"Проходная №15",
			"Проходная №16"
	};
	private DataArrayExamples() {}
	
	public static List<Employee> getArrayListEmployees() {
		
		List<Employee> res = new ArrayList<Employee>(fio.length);
		for(int i = 0; i < fio.length; i++) {
			Employee e = new Employee(new Integer(i).toString(),fio[getRandomNumber( 0, fio.length-1)], getIdRandom(), division[getRandomNumber(0, division.length-1)], position[getRandomNumber(0, position.length-1)]);
			res.add(e);
		}
		return res;
	}
	public static List<PastEmployees> getArrayListPastEmployees() {
		
		List<PastEmployees> res = new ArrayList<PastEmployees>(fio.length);
		for(int i = 0; i < fio.length; i++) {
			PastEmployees e = new PastEmployees(timestamp(), fio[getRandomNumber( 0, fio.length-1)], getIdRandom(), String.valueOf(getRandomNumber(36, 42)), passage[getRandomNumber(0, passage.length-1)]);
			res.add(e);
		}
		return res;
	}
	
	public static String getIdRandom() {
		String res = "";
		for (int i = 0; i < 9;i++) {
			res += String.valueOf(getRandomNumber(0,9)); 
		}
	    return res;
	}
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	public static LocalDateTime timestamp() {
		ZoneOffset zoneOffSet= ZoneOffset.of("+05:00");
 										//max   min     min
		int ir = (int) ((Math.random() * (6_000_000 - 4_000_000)) + 4_000_000);

	    return  LocalDateTime.ofEpochSecond(1_616_000_000L + (long)ir, 0 ,zoneOffSet);
	}
	
}
