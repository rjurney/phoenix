/*
 * Copyright 2010 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.phoenix.expression;

import java.util.Map;

import org.apache.phoenix.expression.function.CoalesceFunction;
import org.apache.phoenix.expression.function.CountAggregateFunction;
import org.apache.phoenix.expression.function.DistinctCountAggregateFunction;
import org.apache.phoenix.expression.function.IndexStateNameFunction;
import org.apache.phoenix.expression.function.InvertFunction;
import org.apache.phoenix.expression.function.LTrimFunction;
import org.apache.phoenix.expression.function.LengthFunction;
import org.apache.phoenix.expression.function.MaxAggregateFunction;
import org.apache.phoenix.expression.function.MinAggregateFunction;
import org.apache.phoenix.expression.function.PercentRankAggregateFunction;
import org.apache.phoenix.expression.function.PercentileContAggregateFunction;
import org.apache.phoenix.expression.function.PercentileDiscAggregateFunction;
import org.apache.phoenix.expression.function.RTrimFunction;
import org.apache.phoenix.expression.function.RegexpReplaceFunction;
import org.apache.phoenix.expression.function.RegexpSubstrFunction;
import org.apache.phoenix.expression.function.ReverseFunction;
import org.apache.phoenix.expression.function.RoundFunction;
import org.apache.phoenix.expression.function.SqlTableType;
import org.apache.phoenix.expression.function.SqlTypeNameFunction;
import org.apache.phoenix.expression.function.StddevPopFunction;
import org.apache.phoenix.expression.function.StddevSampFunction;
import org.apache.phoenix.expression.function.SubstrFunction;
import org.apache.phoenix.expression.function.SumAggregateFunction;
import org.apache.phoenix.expression.function.ToCharFunction;
import org.apache.phoenix.expression.function.ToDateFunction;
import org.apache.phoenix.expression.function.ToNumberFunction;
import org.apache.phoenix.expression.function.TrimFunction;
import org.apache.phoenix.expression.function.TruncFunction;

import com.google.common.collect.Maps;

/**
 * 
 * Enumeration of all Expression types that may be evaluated on the server-side.
 * Used during serialization and deserialization to pass Expression between client
 * and server.
 *
 * @author jtaylor
 * @since 0.1
 */
public enum ExpressionType {
    ReverseFunction(ReverseFunction.class),
    RowKey(RowKeyColumnExpression.class),
    KeyValue(KeyValueColumnExpression.class),
    LiteralValue(LiteralExpression.class),
    RoundFunction(RoundFunction.class),
    TruncFunction(TruncFunction.class),
    ToDateFunction(ToDateFunction.class),
    ToCharFunction(ToCharFunction.class),
    ToNumberFunction(ToNumberFunction.class),
    CoerceFunction(CoerceExpression.class),
    SubstrFunction(SubstrFunction.class),
    AndExpression(AndExpression.class),
    OrExpression(OrExpression.class),
    ComparisonExpression(ComparisonExpression.class),
    CountAggregateFunction(CountAggregateFunction.class),
    SumAggregateFunction(SumAggregateFunction.class),
    MinAggregateFunction(MinAggregateFunction.class),
    MaxAggregateFunction(MaxAggregateFunction.class),
    LikeExpression(LikeExpression.class),
    NotExpression(NotExpression.class),
    CaseExpression(CaseExpression.class),
    InListExpression(InListExpression.class),
    IsNullExpression(IsNullExpression.class),
    LongSubtractExpression(LongSubtractExpression.class),
    DateSubtractExpression(DateSubtractExpression.class),
    DecimalSubtractExpression(DecimalSubtractExpression.class),
    LongAddExpression(LongAddExpression.class),
    DecimalAddExpression(DecimalAddExpression.class),
    DateAddExpression(DateAddExpression.class),
    LongMultiplyExpression(LongMultiplyExpression.class),
    DecimalMultiplyExpression(DecimalMultiplyExpression.class),
    LongDivideExpression(LongDivideExpression.class),
    DecimalDivideExpression(DecimalDivideExpression.class),
    CoalesceFunction(CoalesceFunction.class),
    RegexpReplaceFunction(RegexpReplaceFunction.class),
    SQLTypeNameFunction(SqlTypeNameFunction.class),
    RegexpSubstrFunction(RegexpSubstrFunction.class),
    StringConcatExpression(StringConcatExpression.class),
    LengthFunction(LengthFunction.class),
    LTrimFunction(LTrimFunction.class),
    RTrimFunction(RTrimFunction.class),
    TrimFunction(TrimFunction.class),
    DistinctCountAggregateFunction(DistinctCountAggregateFunction.class),
    PercentileContAggregateFunction(PercentileContAggregateFunction.class),
    PercentRankAggregateFunction(PercentRankAggregateFunction.class),
    StddevPopFunction(StddevPopFunction.class),
    StddevSampFunction(StddevSampFunction.class),
    PercentileDiscAggregateFunction(PercentileDiscAggregateFunction.class),
    DoubleAddExpression(DoubleAddExpression.class),
    DoubleSubtractExpression(DoubleSubtractExpression.class),
    DoubleMultiplyExpression(DoubleMultiplyExpression.class),
    DoubleDivideExpression(DoubleDivideExpression.class),
    RowValueConstructorExpression(RowValueConstructorExpression.class),
    SqlTableType(SqlTableType.class),
    CeilingDecimalExpression(CeilingDecimalExpression.class),
    CeilingTimestampExpression(CeilingTimestampExpression.class),
    FloorDecimalExpression(FloorDecimalExpression.class),
    FloorTimestampExpression(FloorTimestampExpression.class),
    IndexKeyValue(IndexKeyValueColumnExpression.class),
    IndexStateName(IndexStateNameFunction.class),
    InvertFunction(InvertFunction.class),
    TimestampAddExpression(TimestampAddExpression.class),
    TimestampSubtractExpression(TimestampSubtractExpression.class),
    ;
    
    ExpressionType(Class<? extends Expression> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Expression> getExpressionClass() {
        return clazz;
    }

    private final Class<? extends Expression> clazz;

    private static final Map<Class<? extends Expression>,ExpressionType> classToEnumMap = Maps.newHashMapWithExpectedSize(3);
    static {
        for (ExpressionType type : ExpressionType.values()) {
            classToEnumMap.put(type.clazz, type);
        }
    }

    /**
     * Return the ExpressionType for a given Expression instance
     */
    public static ExpressionType valueOf(Expression expression) {
        ExpressionType type = classToEnumMap.get(expression.getClass());
        if (type == null) { // FIXME: this exception gets swallowed and retries happen
            throw new IllegalArgumentException("No ExpressionType for " + expression.getClass());
        }
        return type;
    }

    /**
     * Instantiates a DataAccessor based on its DataAccessorType
     */
    public Expression newInstance() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}