package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportHeader;

public class JSONFactory implements ReportAbstractFactory{

	@Override
	public ReportBody createReportBody() {
		return new JSONReportBody();
	}

	@Override
	public ReportFooter createReportFooter() {
		return new JSONReportFooter();
	}

	@Override
	public ReportHeader createReportHeader() {
		return new JSONReportHeader();
	}


}
