
package org.drip.sample.algo;

import org.drip.numerical.common.*;
import org.drip.service.env.EnvManager;
import org.drip.spaces.big.BigR1Array;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting analytics/risk, transaction cost analytics,
 *  	asset liability management analytics, capital, exposure, and margin analytics, valuation adjustment
 *  	analytics, and portfolio construction analytics within and across fixed income, credit, commodity,
 *  	equity, FX, and structured products. It also includes auxiliary libraries for algorithm support,
 *  	numerical analysis, numerical optimization, spline builder, model validation, statistical learning,
 *  	and computational support.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Product Core - https://lakshmidrip.github.io/DROP-Product-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Computational Core - https://lakshmidrip.github.io/DROP-Computational-Core/
 * 
 * 	DROP Product Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Loan Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 *  - Asset Liability Management Analytics
 * 	- Capital Estimation Analytics
 * 	- Exposure Analytics
 * 	- Margin Analytics
 * 	- XVA Analytics
 * 
 * 	DROP Computational Core implements libraries for the following:
 * 	- Algorithm Support
 * 	- Computation Support
 * 	- Function Analysis
 *  - Model Validation
 * 	- Numerical Analysis
 * 	- Numerical Optimizer
 * 	- Spline Builder
 *  - Statistical Learning
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
 * <i>R1ArrayInSituSort</i> demonstrates the Functionality that conducts an in-place Sorting of an Instance
 * of BigDoubleArray using a variety of Sorting Algorithms.
 * 
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/StatisticalLearningLibrary.md">Statistical Learning Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">DROP API Construction and Usage</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/algo/README.md">C<sup>x</sup> R<sup>x</sup> In-Place Manipulation</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class R1ArrayInSituSort {

	private static void QuickSort (
		final int iNumRandom,
		final String strPrefix)
		throws Exception
	{
		double[] adblA = new double[iNumRandom];
		double[] adblAOrig = new double[iNumRandom];

		for (int i = 0; i < iNumRandom; ++i) {
			adblA[i] = Math.random();

			adblAOrig[i] = adblA[i];
		}

		BigR1Array ba = new BigR1Array (adblA);

		ba.quickSort();

		System.out.println ("\n\t---------------------------------------------");

		for (int i = 0; i < iNumRandom; ++i)
			System.out.println (
				"\t||  " + strPrefix + "  " +
				FormatUtil.FormatDouble (adblAOrig[i], 1, 4, 1.) + " | " +
				FormatUtil.FormatDouble (adblA[i], 1, 4, 1.) + " ||"
			);

		System.out.println ("\t---------------------------------------------");
	}

	private static void MergeSort (
		final int iNumRandom,
		final String strPrefix)
		throws Exception
	{
		double[] adblA = new double[iNumRandom];
		double[] adblAOrig = new double[iNumRandom];

		for (int i = 0; i < iNumRandom; ++i) {
			adblA[i] = Math.random();

			adblAOrig[i] = adblA[i];
		}

		BigR1Array ba = new BigR1Array (adblA);

		ba.mergeSort();

		System.out.println ("\n\t---------------------------------------------");

		for (int i = 0; i < iNumRandom; ++i)
			System.out.println (
				"\t||  " + strPrefix + "  " +
				FormatUtil.FormatDouble (adblAOrig[i], 1, 4, 1.) + " | " +
				FormatUtil.FormatDouble (adblA[i], 1, 4, 1.) + " ||"
			);

		System.out.println ("\t---------------------------------------------");
	}

	private static void HeapSort (
		final int iNumRandom,
		final String strPrefix)
		throws Exception
	{
		double[] adblA = new double[iNumRandom];
		double[] adblAOrig = new double[iNumRandom];

		for (int i = 0; i < iNumRandom; ++i) {
			adblA[i] = Math.random();

			adblAOrig[i] = adblA[i];
		}

		BigR1Array ba = new BigR1Array (adblA);

		ba.heapSort();

		System.out.println ("\n\t---------------------------------------------");

		for (int i = 0; i < iNumRandom; ++i)
			System.out.println (
				"\t||  " + strPrefix + "  " +
				FormatUtil.FormatDouble (adblAOrig[i], 1, 4, 1.) + " | " +
				FormatUtil.FormatDouble (adblA[i], 1, 4, 1.) + " ||"
			);

		System.out.println ("\t---------------------------------------------");
	}

	private static void InsertionSort (
		final int iNumRandom,
		final String strPrefix)
		throws Exception
	{
		double[] adblA = new double[iNumRandom];
		double[] adblAOrig = new double[iNumRandom];

		for (int i = 0; i < iNumRandom; ++i) {
			adblA[i] = Math.random();

			adblAOrig[i] = adblA[i];
		}

		System.out.println ("\n\t---------------------------------------------");

		NumberUtil.Print1DArray (
			"\t|  ORIGINAL  ",
			adblA,
			6,
			false
		);

		System.out.println ("\t---------------------------------------------");

		BigR1Array ba = new BigR1Array (adblA);

		ba.insertionSort();

		System.out.println ("\n\t---------------------------------------------");

		for (int i = 0; i < iNumRandom; ++i)
			System.out.println (
				"\t||  " + strPrefix + "  " +
				FormatUtil.FormatDouble (adblAOrig[i], 1, 4, 1.) + " | " +
				FormatUtil.FormatDouble (adblA[i], 1, 4, 1.) + " ||"
			);

		System.out.println ("\t---------------------------------------------");
	}

	public static void main (
		final String[] astrArgs)
		throws Exception
	{
		EnvManager.InitEnv (
			"",
			true
		);

		int iNumRandom = 50;

		QuickSort (
			iNumRandom,
			"QUICKSORT"
		);

		MergeSort (
			iNumRandom,
			"MERGESORT"
		);

		InsertionSort (
			iNumRandom,
			"INSERTIONSORT"
		);

		HeapSort (
			iNumRandom,
			"HEAPSORT"
		);

		EnvManager.TerminateEnv();
	}
}
