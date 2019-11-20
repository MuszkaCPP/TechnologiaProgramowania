package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

public interface ReportAbstractFactory{
	ReportBody createReportBody();
	ReportFooter createReportFooter();
	ReportHeader createReportHeader();
}
