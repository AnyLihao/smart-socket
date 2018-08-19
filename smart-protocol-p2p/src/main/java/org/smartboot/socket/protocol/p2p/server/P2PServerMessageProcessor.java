package org.smartboot.socket.protocol.p2p.server;

import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.extension.processor.AbstractMessageProcessor;
import org.smartboot.socket.protocol.p2p.MessageHandler;
import org.smartboot.socket.protocol.p2p.P2PSession;
import org.smartboot.socket.protocol.p2p.message.BaseMessage;
import org.smartboot.socket.protocol.p2p.message.P2pServiceMessageFactory;
import org.smartboot.socket.transport.AioSession;

/**
 * 服务器消息处理器,由服务器启动时构造
 *
 * @author 三刀
 */
public final class P2PServerMessageProcessor extends AbstractMessageProcessor<BaseMessage> {
    private P2pServiceMessageFactory serviceMessageFactory;

    public P2PServerMessageProcessor(P2pServiceMessageFactory serviceMessageFactory) {
        this.serviceMessageFactory = serviceMessageFactory;
    }

    @Override
    public void process0(AioSession<BaseMessage> ioSession, BaseMessage entry) {
        P2PSession session = ioSession.getAttachment();
        if (session.notifySyncMessage(entry)) {
            return;
        }
        MessageHandler handler = serviceMessageFactory.getProcessor(entry.getClass());
        handler.handler(session, entry);
    }

    @Override
    public void stateEvent0(AioSession<BaseMessage> session, StateMachineEnum stateMachineEnum, Throwable throwable) {
        switch (stateMachineEnum) {
            case NEW_SESSION:
                session.setAttachment(new P2PSession(session));
                break;
            case FLOW_LIMIT:
//                System.out.println("flow limit");
                break;
            case RELEASE_FLOW_LIMIT:
//                System.out.println("release flow limit");
                break;
            case INPUT_SHUTDOWN:
//                session.close(true);
                break;
        }

    }

}
