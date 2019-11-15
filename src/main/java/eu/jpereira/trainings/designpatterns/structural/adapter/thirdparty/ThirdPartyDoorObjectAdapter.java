package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.DoorState;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.LockStatus;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door{

	private ThirdPartyDoor Door;
	
	public ThirdPartyDoorObjectAdapter() {
		Door = new ThirdPartyDoor();
	}
	
	@Override
	public void open(String code) throws IncorrectDoorCodeException {
		try {
			Door.unlock(code);
		} catch (CannotUnlockDoorException e) {
			throw new IncorrectDoorCodeException();
		}
		
	}

	@Override
	public void close() {
		if(Door.getLockStatus().equals(LockStatus.UNLOCKED)) {
			try {
				Door.setState(DoorState.CLOSED);
				Door.lock();
			} catch (CannotChangeStateOfLockedDoor e) {
				
			}
		}
		
	}

	@Override
	public boolean isOpen() {
		if(Door.getLockStatus() == LockStatus.LOCKED) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void changeCode(String oldCode, String newCode, String newCodeConfirmation)
			throws IncorrectDoorCodeException, CodeMismatchException {
		try {
			Door.unlock(oldCode);
			if(newCode.equals(newCodeConfirmation)) {
				try {
					Door.setNewLockCode(newCode);
				} catch (CannotChangeCodeForUnlockedDoor e) {
				}
			}
			else {throw new CodeMismatchException();}
		} catch (CannotUnlockDoorException e) {
			throw new IncorrectDoorCodeException();
		}
		
	}

	@Override
	public boolean testCode(String code) {
		try {
			Door.unlock(code);
			return true;
		} catch (CannotUnlockDoorException e) {
			return false;
		}

	}

}
