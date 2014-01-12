/*
// Licensed to Julian Hyde under one or more contributor license
// agreements. See the NOTICE file distributed with this work for
// additional information regarding copyright ownership.
//
// Julian Hyde licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except in
// compliance with the License. You may obtain a copy of the License at:
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
*/
package net.hydromatic.optiq.tools;

import org.eigenbase.relopt.RelOptRule;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.Iterator;

/**
 * Utilities for creating and composing rule sets.
 *
 * @see net.hydromatic.optiq.tools.RuleSet
 */
public class RuleSets {
  private RuleSets() {
  }

  /** Creates a rule set with a given array of rules. */
  public static RuleSet ofList(RelOptRule... rules) {
    return new ListRuleSet(ImmutableList.copyOf(rules));
  }

  /** Creates a rule set with a given collection of rules. */
  public static RuleSet ofList(Collection<RelOptRule> rules) {
    return new ListRuleSet(ImmutableList.copyOf(rules));
  }

  /** Rule set that consists of a list of rules. */
  private static class ListRuleSet implements RuleSet {
    private final ImmutableList<RelOptRule> rules;

    public ListRuleSet(ImmutableList<RelOptRule> rules) {
      this.rules = rules;
    }

    @Override
    public int hashCode() {
      return rules.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      return obj == this
          || obj instanceof ListRuleSet
          && rules.equals(((ListRuleSet) obj).rules);
    }

    public Iterator<RelOptRule> iterator() {
      return rules.iterator();
    }
  }
}

// End RuleSets.java