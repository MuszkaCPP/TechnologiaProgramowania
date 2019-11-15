package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door{

	
	public ThirdPartyDoorAdapter() {
		
	}
	
	@Override
	public void open(String code) throws IncorrectDoorCodeException {
		try {
			unlock(code);
		} catch (CannotUnlockDoorException e) {
			throw new IncorrectDoorCodeException();
		}
	}

	@Override
	public void close() {
		if(getLockStatus().equals(LockStatus.UNLOCKED)) {
			try {
				setState(DoorState.CLOSED);
				lock();
			} catch (CannotChangeStateOfLockedDoor e) {
				
			}
		}
	}

	@Override
	public boolean isOpen() {
		if(getLockStatus() == LockStatus.LOCKED) {
			return false;
		}else {
			return true;
		}

	}

	@Override
	public void changeCode(String oldCode, String newCode, String newCodeConfirmation)
			throws IncorrectDoorCodeException, CodeMismatchException {
		try {
			unlock(oldCode);
			if(newCode.equals(newCodeConfirmation)) {
				try {
					setNewLockCode(newCode);
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
			unlock(code);
			return true;
		} catch (CannotUnlockDoorException e) {
			return false;
		}

	}

}
