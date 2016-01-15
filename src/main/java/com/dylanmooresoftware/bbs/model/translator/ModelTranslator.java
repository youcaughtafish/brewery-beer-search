package com.dylanmooresoftware.bbs.model.translator;

/**
 * 
 * Translate one model class into another.
 * @param <I> input type
 * @param <O> output type
 */
public interface ModelTranslator<I, O> {
  O translate(I i);
}
