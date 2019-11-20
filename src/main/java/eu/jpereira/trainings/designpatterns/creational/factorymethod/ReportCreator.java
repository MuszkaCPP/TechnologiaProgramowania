package eu.jpereira.trainings.designpatterns.creational.factorymethod;

import eu.jpereira.trainings.designpatterns.creational.factorymethod.ReportGenerator.reportType;

public class ReportCreator {

	public Report createReport(reportType _reportType) {
		switch (_reportType) {
			case JSON:
				return new JSONReport();
			case XML:
				return new XMLReport();
			case HTML:
				return new HTMLReport();
			case PDF:
				return new PDFReport();
			default:
				return null;
		}
	
	}
	
}
