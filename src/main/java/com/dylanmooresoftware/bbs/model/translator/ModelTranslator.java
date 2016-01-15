package com.dylanmooresoftware.bbs.model.translator;

public interface ModelTranslator<I, O> {
  O translate(I i);
}
