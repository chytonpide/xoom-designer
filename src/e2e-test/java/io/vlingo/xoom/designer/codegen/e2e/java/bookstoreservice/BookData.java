// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package io.vlingo.xoom.designer.codegen.e2e.java.bookstoreservice;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class BookData {
  public String id;
  public final String title;
  public final int stockCode;
  public final byte publicCode;
  public final double price;
  public final float weight;
  public final long height;
  public final boolean available;
  public final char symbol;

  public static BookData sampleOfInitialData() {
    return new BookData("", "IDDD", 987, (byte) 1, 457.25, 150, 10, true, 'a');
  }

  public static BookData sampleOfChangedPrice() {
    return new BookData("", "IDDD", 987, (byte) 1, 478.25, 150, 10, true, 'a');
  }

  private BookData(final String id,
                   final String title,
                   final int stockCode,
                   final byte publicCode,
                   final double price,
                   final float weight,
                   final long height,
                   final boolean available,
                   final char symbol) {
    this.id = id;
    this.title = title;
    this.stockCode = stockCode;
    this.publicCode = publicCode;
    this.price = price;
    this.weight = weight;
    this.height = height;
    this.available = available;
    this.symbol = symbol;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    BookData another = (BookData) other;
    return new EqualsBuilder()
            .append(this.title, another.title)
            .append(this.stockCode, another.stockCode)
            .append(this.publicCode, another.publicCode)
            .append(this.price, another.price)
            .append(this.weight, another.weight)
            .append(this.height, another.height)
            .append(this.available, another.available)
            .append(this.symbol, another.symbol)
            .isEquals();
  }
}
