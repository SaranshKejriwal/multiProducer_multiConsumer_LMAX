package lmaxUtils;

import com.lmax.disruptor.EventFactory;

import message.MsgObject;

public class MsgFactory implements EventFactory<MsgObject>
{
	//we have to implement EventFactory<T>.newInstance()
    public MsgObject newInstance()//create a new Bean
    {
        return new MsgObject(new String(),100);
    }
}
