/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.wubin.sunflowermodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.wubin.sunflowermodule.adapters.GardenPlantingAdapter
import com.example.wubin.sunflowermodule.databinding.FragmentGardenBinding
import com.example.wubin.sunflowermodule.utilities.InjectorUtils
import com.example.wubin.sunflowermodule.viewmodels.GardenPlantingListViewModel

class GardenFragment : Fragment() {

    private val viewModel: GardenPlantingListViewModel by viewModels {
        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGardenBinding.inflate(inflater, container, false)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
        viewModel.gardenPlantings.observe(viewLifecycleOwner) { plantings ->
            binding.hasPlantings = !plantings.isNullOrEmpty()
        }

        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
            if (!result.isNullOrEmpty())
                adapter.submitList(result)
        }
    }
}
