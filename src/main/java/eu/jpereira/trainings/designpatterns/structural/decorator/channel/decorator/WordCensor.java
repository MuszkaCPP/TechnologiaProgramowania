package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

public class WordCensor extends SocialChannelDecorator{

	private final String _censoredWord;
	
	WordCensor(String censoredWord){
		_censoredWord = censoredWord;
	}
	
	@Override
	public void deliverMessage(String message) {
		String censoredMessage = message.replace(_censoredWord, "###");
		delegate.deliverMessage(censoredMessage);
	}
	
}
