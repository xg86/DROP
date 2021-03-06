
package org.drip.sample.bondapi;

import java.util.Map;

import org.drip.analytics.date.*;
import org.drip.service.env.EnvManager;
import org.drip.service.product.FixedBondAPI;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting risk, transaction costs, exposure, margin
 *  	calculations, valuation adjustment, and portfolio construction within and across fixed income,
 *  	credit, commodity, equity, FX, and structured products.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Analytics Core - https://lakshmidrip.github.io/DROP-Analytics-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Numerical Core - https://lakshmidrip.github.io/DROP-Numerical-Core/
 * 
 * 	DROP Analytics Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Asset Backed Analytics
 * 	- XVA Analytics
 * 	- Exposure and Margin Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Numerical Core implements libraries for the following:
 * 	- Statistical Learning
 * 	- Numerical Optimizer
 * 	- Spline Builder
 * 	- Algorithm Support
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Repo Layout Taxonomy     => https://github.com/lakshmiDRIP/DROP/blob/master/Taxonomy.md
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
 * 	- JUnit                    => https://lakshmidrip.github.io/DROP/junit/index.html
 * 	- Jacoco                   => https://lakshmidrip.github.io/DROP/jacoco/index.html
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *   
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

/**
 * <i>FixedCoupon</i> demonstrates the Invocation and Examination of the Metrics for the Fixed Coupon Bond.
 *  
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/AnalyticsCore.md">Analytics Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/FixedIncomeAnalyticsLibrary.md">Fixed Income Analytics Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">Sample</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/bondapi/README.md">Bond API</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class FixedCoupon {

	public static final void main (
		final String[] astrArgs)
		throws Exception
	{
		EnvManager.InitEnv (
			"",
			true
		);

		int iSpotDate = DateUtil.CreateFromYMD (
			2015,
			DateUtil.NOVEMBER,
			18
		).julian();

		String[] astrFundingCurveDepositTenor = new String[] {
			"2D",
			"1W",
			"1M",
			"2M",
			"3M"
		};

		double[] adblFundingCurveDepositQuote = new double[] {
			0.00195, // 2D
			0.00176, // 1W
			0.00301, // 1M
			0.00401, // 2M
			0.00492  // 3M
		};

		String strFundingCurveDepositMeasure = "ForwardRate";

		double[] adblFundingCurveFuturesQuote = new double[] {
			0.00609,
			0.00687
		};

		String strFundingCurveFuturesMeasure = "ForwardRate";

		String[] astrFundingCurveFixFloatTenor = new String[] {
			"01Y",
			"02Y",
			"03Y",
			"04Y",
			"05Y",
			"06Y",
			"07Y",
			"08Y",
			"09Y",
			"10Y",
			"11Y",
			"12Y",
			"15Y",
			"20Y",
			"25Y",
			"30Y",
			"40Y",
			"50Y"
		};

		double[] adblFundingCurveFixFloatQuote = new double[] {
			0.00762, //  1Y
			0.01055, //  2Y
			0.01300, //  3Y
			0.01495, //  4Y
			0.01651, //  5Y
			0.01787, //  6Y
			0.01904, //  7Y
			0.02005, //  8Y
			0.02090, //  9Y
			0.02166, // 10Y
			0.02231, // 11Y
			0.02289, // 12Y
			0.02414, // 15Y
			0.02570, // 20Y
			0.02594, // 25Y
			0.02627, // 30Y
			0.02648, // 40Y
			0.02632  // 50Y
		};

		String strFundingFixFloatMeasure = "SwapRate";

		int[] aiGovvieCurveTreasuryEffectiveDate = new int[] {
			iSpotDate,
			iSpotDate,
			iSpotDate,
			iSpotDate,
			iSpotDate,
			iSpotDate,
			iSpotDate
		};

		int[] aiGovvieCurveTreasuryMaturityDate = new int[] {
			new JulianDate (iSpotDate).addTenor ("1Y").julian(),
			new JulianDate (iSpotDate).addTenor ("2Y").julian(),
			new JulianDate (iSpotDate).addTenor ("3Y").julian(),
			new JulianDate (iSpotDate).addTenor ("5Y").julian(),
			new JulianDate (iSpotDate).addTenor ("7Y").julian(),
			new JulianDate (iSpotDate).addTenor ("10Y").julian(),
			new JulianDate (iSpotDate).addTenor ("30Y").julian()
		};

		double[] adblGovvieCurveTreasuryCoupon = new double[] {
			0.0100,
			0.0100,
			0.0125,
			0.0150,
			0.0200,
			0.0225,
			0.0300
		};

		double[] adblGovvieCurveTreasuryYield = new double[] {
			0.00692,
			0.00945,
			0.01257,
			0.01678,
			0.02025,
			0.02235,
			0.02972
		};

		String strGovvieCurveTreasuryMeasure = "Yield";

		String[] astrCreditCurveCDSTenor = new String[] {
			"06M",
			"01Y",
			"02Y",
			"03Y",
			"04Y",
			"05Y",
			"07Y",
			"10Y"
		};

		double[] adblCreditCurveCDSCoupon = new double[] {
			 60.,	//  6M
			 68.,	//  1Y
			 88.,	//  2Y
			102.,	//  3Y
			121.,	//  4Y
			138.,	//  5Y
			168.,	//  7Y
			188.	// 10Y
		};

		String strIssuerName = "AEG";
		double dblBondCoupon = 0.0560;
		int iBondCouponFrequency = 2;
		String strBondCouponDayCount = "30/360";
		String strBondCouponCurrency = "USD";
		String strBondMarketQuoteName = "Price";
		double dblBondMarketQuote = 1.17460;
		String strGovvieCode = "UST";

		int iBondEffectiveDate = DateUtil.CreateFromYMD (
			2011,
			DateUtil.JULY,
			21
		).julian();

		int iBondMaturityDate = DateUtil.CreateFromYMD (
			2041,
			DateUtil.JULY,
			15
		).julian();

		Map<String, Double> mapBondMetrics = FixedBondAPI.ValuationMetrics (
			strIssuerName,
			iBondEffectiveDate,
			iBondMaturityDate,
			dblBondCoupon,
			iBondCouponFrequency,
			strBondCouponDayCount,
			strBondCouponCurrency,
			iSpotDate,
			astrFundingCurveDepositTenor,
			adblFundingCurveDepositQuote,
			strFundingCurveDepositMeasure,
			adblFundingCurveFuturesQuote,
			strFundingCurveFuturesMeasure,
			astrFundingCurveFixFloatTenor,
			adblFundingCurveFixFloatQuote,
			strFundingFixFloatMeasure,
			strGovvieCode,
			aiGovvieCurveTreasuryEffectiveDate,
			aiGovvieCurveTreasuryMaturityDate,
			adblGovvieCurveTreasuryCoupon,
			adblGovvieCurveTreasuryYield,
			strGovvieCurveTreasuryMeasure,
			strIssuerName,
			astrCreditCurveCDSTenor,
			adblCreditCurveCDSCoupon,
			adblCreditCurveCDSCoupon,
			"FairPremium",
			strBondMarketQuoteName,
			dblBondMarketQuote
		);

		for (Map.Entry<String, Double> me : mapBondMetrics.entrySet())
			System.out.println ("\t" + me.getKey() + " => " + me.getValue());

		EnvManager.TerminateEnv();
	}
}
