package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;

public class WordCensorTest extends AbstractSocialChanneldDecoratorTest{
	@Test
	public void testCensor() {
		SocialChannelBuilder builder = createTestSpySocialChannelBuilder();
		
		SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
		SocialChannel channel = builder.with(new WordCensor("Windows")).getDecoratedChannel(props);
		
		channel.deliverMessage("Windows is great!");
		
		TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
		assertEquals("### is great!", spyChannel.lastMessagePublished());	
	}
	
	@Test
	public void myDecoratorTestChain() {
		
		SocialChannelBuilder builder = createTestSpySocialChannelBuilder();
		SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

		SocialChannel channel = builder.
				with(new WordCensor("Microsoft")).
				with(new URLAppender("http://youtube.com")).
				with(new MessageTruncator(54)).
				getDecoratedChannel(props);

		channel.deliverMessage("Microsoft Windows is great! Check it there :");
		
		TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
		
		assertEquals("### Windows is great! Check it there : http://youtu...", spyChannel.lastMessagePublished());
	}
}
