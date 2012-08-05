package dsq.cacophony.operation.common;

import dsq.cacophony.grate.*;

// FIX 3/03/12 This will mutate. Deal with it.
public interface Operation {
    boolean fold(ChannelOp channels, ControlOp controls, SystemOp systems, UiOp ui, NoOp noop);
}





/*
  return new Pop(ds;faj, ajsdf;asd , asdfj;askdfj);


  meanwhile:


   var pop = Pop;
   pop.fold(new ChannelOP () {
      go (Channel channel) {
        adf;s;f
      ) {


   And then in channel Pop

    fold (f, g, h, i ) {
      f (stuff it knows)
    }
           */