package io.ebeaninternal.server.core;

import io.ebean.bean.CallStack;

/**
 * Default CallStackFactory where the Hash function for StackTraceElement includes the line number.
 */
public class DefaultCallStackFactory implements CallStackFactory {

  @Override
  public CallStack createCallStack(StackTraceElement[] finalTrace) {
    return new CallStack(finalTrace, finalTrace[0].hashCode(), pathHash(finalTrace));
  }

  /**
   * Return the hash code for the path excluding the first element.
   */
  private int pathHash(StackTraceElement[] callStack) {

    int hc = 0;
    for (int i = 1; i < callStack.length; i++) {
      hc = 92821 * hc + callStack[i].hashCode();
    }
    return hc;
  }

}
