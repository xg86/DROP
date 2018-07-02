
package org.drip.execution.optimum;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * 
 *  This file is part of DRIP, a free-software/open-source library for buy/side financial/trading model
 *  	libraries targeting analysts and developers
 *  	https://lakshmidrip.github.io/DRIP/
 *  
 *  DRIP is composed of four main libraries:
 *  
 *  - DRIP Fixed Income - https://lakshmidrip.github.io/DRIP-Fixed-Income/
 *  - DRIP Asset Allocation - https://lakshmidrip.github.io/DRIP-Asset-Allocation/
 *  - DRIP Numerical Optimizer - https://lakshmidrip.github.io/DRIP-Numerical-Optimizer/
 *  - DRIP Statistical Learning - https://lakshmidrip.github.io/DRIP-Statistical-Learning/
 * 
 *  - DRIP Fixed Income: Library for Instrument/Trading Conventions, Treasury Futures/Options,
 *  	Funding/Forward/Overnight Curves, Multi-Curve Construction/Valuation, Collateral Valuation and XVA
 *  	Metric Generation, Calibration and Hedge Attributions, Statistical Curve Construction, Bond RV
 *  	Metrics, Stochastic Evolution and Option Pricing, Interest Rate Dynamics and Option Pricing, LMM
 *  	Extensions/Calibrations/Greeks, Algorithmic Differentiation, and Asset Backed Models and Analytics.
 * 
 *  - DRIP Asset Allocation: Library for model libraries for MPT framework, Black Litterman Strategy
 *  	Incorporator, Holdings Constraint, and Transaction Costs.
 * 
 *  - DRIP Numerical Optimizer: Library for Numerical Optimization and Spline Functionality.
 * 
 *  - DRIP Statistical Learning: Library for Statistical Evaluation and Machine Learning.
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
 * AlmgrenChrissDriftDiscrete contains the Trading Trajectory generated by the Almgren and Chriss (2000)
 *  Scheme under the Criterion of Non-zero Drift. The References are:
 * 
 * 	- Almgren, R., and N. Chriss (1999): Value under Liquidation, Risk 12 (12).
 * 
 * 	- Almgren, R., and N. Chriss (2000): Optimal Execution of Portfolio Transactions, Journal of Risk 3 (2)
 * 		5-39.
 * 
 * 	- Bertsimas, D., and A. W. Lo (1998): Optimal Control of Execution Costs, Journal of Financial Markets,
 * 		1, 1-50.
 *
 * 	- Chan, L. K. C., and J. Lakonishak (1995): The Behavior of Stock Prices around Institutional Trades,
 * 		Journal of Finance, 50, 1147-1174.
 *
 * 	- Keim, D. B., and A. Madhavan (1997): Transaction Costs and Investment Style: An Inter-exchange
 * 		Analysis of Institutional Equity Trades, Journal of Financial Economics, 46, 265-292.
 * 
 * @author Lakshmi Krishnamurthy
 */

public class AlmgrenChrissDriftDiscrete extends org.drip.execution.optimum.AlmgrenChrissDiscrete {
	private double[] _adblHoldingsDriftAdjustment = null;
	private double[] _adblTradeListDriftAdjustment = null;
	private double _dblResidualHolding = java.lang.Double.NaN;
	private double _dblDriftGainUpperBound = java.lang.Double.NaN;

	/**
	 * AlmgrenChrissDriftDiscrete Constructor
	 * 
	 * @param adblExecutionTimeNode Array containing the Trajectory Time Nodes
	 * @param adblHoldings Array containing the Holdings
	 * @param adblTradeList Array containing the Trade List
	 * @param adblHoldingsDriftAdjustment Array containing the Holdings Drift Adjustment
	 * @param adblTradeListDriftAdjustment Array containing the Trade List Drift Adjustment
	 * @param dblKappaTilda AC2000 Kappa-Tilda
	 * @param dblKappa AC2000 Kappa
	 * @param dblResidualHolding The Residual Holdings induced by the Drift
	 * @param dblDriftGainUpperBound The Upper Bound of the Gain induced by Drift
	 * @param dblTransactionCostExpectation The Expected Transaction Cost
	 * @param dblTransactionCostVariance The Variance of the Transaction Cost
	 * @param dblMarketPower Estimate of the Relative Market Impact Power
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public AlmgrenChrissDriftDiscrete (
		final double[] adblExecutionTimeNode,
		final double[] adblHoldings,
		final double[] adblTradeList,
		final double[] adblHoldingsDriftAdjustment,
		final double[] adblTradeListDriftAdjustment,
		final double dblKappaTilda,
		final double dblKappa,
		final double dblResidualHolding,
		final double dblDriftGainUpperBound,
		final double dblTransactionCostExpectation,
		final double dblTransactionCostVariance,
		final double dblMarketPower)
		throws java.lang.Exception
	{
		super (adblExecutionTimeNode, adblHoldings, adblTradeList, dblKappaTilda, dblKappa,
			dblTransactionCostExpectation, dblTransactionCostVariance, dblMarketPower);

		if (null == (_adblHoldingsDriftAdjustment = adblHoldingsDriftAdjustment) || null ==
			(_adblTradeListDriftAdjustment = adblTradeListDriftAdjustment) ||
				!org.drip.quant.common.NumberUtil.IsValid (_dblResidualHolding = dblResidualHolding) ||
					!org.drip.quant.common.NumberUtil.IsValid (_dblDriftGainUpperBound =
						dblDriftGainUpperBound))
			throw new java.lang.Exception ("AlmgrenChrissDriftDiscrete Constructor => Invalid Inputs");

		int iNumNode = _adblHoldingsDriftAdjustment.length;

		if (0 == iNumNode || iNumNode != _adblTradeListDriftAdjustment.length + 1)
			throw new java.lang.Exception ("AlmgrenChrissDriftDiscrete Constructor => Invalid Inputs");

		for (int i = 0; i < iNumNode; ++i) {
			if (!org.drip.quant.common.NumberUtil.IsValid (_adblHoldingsDriftAdjustment[i]))
				throw new java.lang.Exception ("AlmgrenChrissDriftDiscrete Constructor => Invalid Inputs");

			if (0 != i) {
				if (!org.drip.quant.common.NumberUtil.IsValid (_adblTradeListDriftAdjustment[i - 1]))
					throw new java.lang.Exception
						("AlmgrenChrissDriftDiscrete Constructor => Invalid Inputs");
			}
		}
	}

	/**
	 * Retrieve the Array of the Holdings Drift Adjustment
	 * 
	 * @return The Array of the Holdings Drift Adjustment
	 */

	public double[] holdingsDriftAdjustment()
	{
		return _adblHoldingsDriftAdjustment;
	}

	/**
	 * Retrieve the Array of the Trade List Drift Adjustment
	 * 
	 * @return The Array of the Trade List Drift Adjustment
	 */

	public double[] tradeListDriftAdjustment()
	{
		return _adblTradeListDriftAdjustment;
	}

	/**
	 * Retrieve the Residual Holdings induced by the Drift
	 * 
	 * @return The Residual Holdings induced by the Drift
	 */

	public double residualHolding()
	{
		return _dblResidualHolding;
	}

	/**
	 * Retrieve the Gain Upper Bound induced by the Drift
	 * 
	 * @return The Gain Upper Bound induced by the Drift
	 */

	public double driftGainUpperBound()
	{
		return _dblDriftGainUpperBound;
	}
}
