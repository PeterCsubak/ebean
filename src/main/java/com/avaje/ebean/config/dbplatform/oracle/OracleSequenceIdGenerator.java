package com.avaje.ebean.config.dbplatform.oracle;

import com.avaje.ebean.BackgroundExecutor;
import com.avaje.ebean.config.dbplatform.SequenceIdGenerator;

import javax.sql.DataSource;

/**
 * Oracle specific sequence Id Generator.
 */
public class OracleSequenceIdGenerator extends SequenceIdGenerator {

  private final String baseSql;

  /**
   * Construct given a dataSource and sql to return the next sequence value.
   */
  public OracleSequenceIdGenerator(BackgroundExecutor be, DataSource ds, String seqName, int batchSize) {
    super(be, ds, seqName, batchSize);
    this.baseSql = "select " + seqName + ".nextval, a from (select level as a FROM dual CONNECT BY level <= ";
  }

  public String getSql(int batchSize) {
    return baseSql + batchSize + ")";
  }
}