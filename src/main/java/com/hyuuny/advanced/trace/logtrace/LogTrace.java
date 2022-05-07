package com.hyuuny.advanced.trace.logtrace;

import com.hyuuny.advanced.trace.TraceStatus;

public interface LogTrace {

  TraceStatus begin(String message);

  void end(TraceStatus status);

  void exception(TraceStatus status, Exception e);

}
