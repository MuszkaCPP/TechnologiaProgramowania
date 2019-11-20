package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportHeader;

public class XMLFactory implements ReportAbstractFactory{

	@Override
	public ReportBody createReportBody() {
		return new XMLReportBody();
	}

	@Override
	public ReportFooter createReportFooter() {
		return new XMLReportFooter();
	}

	@Override
	public ReportHeader createReportHeader() {
		return new XMLReportHeader();
	}

}
