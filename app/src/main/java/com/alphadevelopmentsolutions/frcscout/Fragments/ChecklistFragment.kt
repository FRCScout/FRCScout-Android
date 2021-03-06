package com.alphadevelopmentsolutions.frcscout.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphadevelopmentsolutions.frcscout.Adapters.ChecklistItemListRecyclerViewAdapter
import com.alphadevelopmentsolutions.frcscout.Adapters.MatchListRecyclerViewAdapter
import com.alphadevelopmentsolutions.frcscout.Classes.Tables.ChecklistItem
import com.alphadevelopmentsolutions.frcscout.Classes.Tables.Match
import com.alphadevelopmentsolutions.frcscout.Classes.Tables.Team
import com.alphadevelopmentsolutions.frcscout.R
import java.util.*

class ChecklistFragment : MasterFragment()
{
    override fun onBackPressed(): Boolean
    {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        loadMatchesThread = Thread(Runnable {
            loadingThread.join()

            matches = Match.getObjects(event, null, team, database)
            searchedMatches = ArrayList(matches)
        })

        loadMatchesThread.start()
    }

    private lateinit var matches: ArrayList<Match>
    private lateinit var searchedMatches: ArrayList<Match>

    private lateinit var loadMatchesThread: Thread

    private var previousSearchLength: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view: View

        val recyclerView: RecyclerView

        context.isToolbarScrollable = true

        loadMatchesThread.join()

        //no match selected, show match list
        if (match == null)
        {
            view = inflater.inflate(R.layout.fragment_match_list, container, false)

            recyclerView = view.findViewById(R.id.MatchListRecyclerView)

            val matchListRecyclerViewAdapter = MatchListRecyclerViewAdapter(event!!, team, searchedMatches, context, this.javaClass)
            recyclerView.adapter = matchListRecyclerViewAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            context.setToolbarTitle(context.getString(R.string.checklist))
            context.isToolbarScrollable = true
            context.isSearchViewVisible = true

            context.setSearchViewOnTextChangeListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean
                {
                    return false
                }

                override fun onQueryTextChange(searchText: String?): Boolean
                {
                    val searchLength = searchText?.length ?: 0

                    //You only need to reset the list if you are removing from your search, adding the objects back
                    if (searchLength < previousSearchLength)
                    {
                        //Reset the list
                        for (i in matches.indices)
                        {
                            val match = matches[i]

                            //check if the contact doesn't exist in the viewable list
                            if (!searchedMatches.contains(match))
                            {
                                //add it and notify the recyclerview
                                searchedMatches.add(i, match)
                                matchListRecyclerViewAdapter.notifyItemInserted(i)
                                matchListRecyclerViewAdapter.notifyItemRangeChanged(i, searchedMatches.size)
                            }
                        }
                    }

                    //Delete from the list
                    var i = 0
                    while (i < searchedMatches.size)
                    {
                        val match = searchedMatches[i]
                        val name = match.toString()

                        //If the contacts name doesn't equal the searched name
                        if (!name.toLowerCase().contains(searchText.toString().toLowerCase()))
                        {
                            //remove it from the list and notify the recyclerview
                            searchedMatches.removeAt(i)
                            matchListRecyclerViewAdapter.notifyItemRemoved(i)
                            matchListRecyclerViewAdapter.notifyItemRangeChanged(i, searchedMatches.size)

                            //this prevents the index from passing the size of the list,
                            //stays on the same index until you NEED to move to the next one
                            i--
                        }
                        i++
                    }

                    previousSearchLength = searchLength

                    return false
                }
            })
        }
        else
        {

            context.lockDrawerLayout(true, View.OnClickListener { context.onBackPressed() })
            view = inflater.inflate(R.layout.fragment_checklist, container, false)
            recyclerView = view.findViewById(R.id.ChecklistItemsRecyclerView)

            val checklistItemListRecyclerViewAdapter = ChecklistItemListRecyclerViewAdapter(match!!, ChecklistItem.getObjects(null, database)!!, context)
            recyclerView.adapter = checklistItemListRecyclerViewAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            context.setToolbarTitle(match.toString())
            context.isToolbarScrollable = false
            context.isSearchViewVisible = false
        }//match and event selected, show the checklist


        return view
    }

    override fun onDestroyView()
    {
        if (match != null)
            context.unlockDrawerLayout()
        
        super.onDestroyView()
    }

    companion object
    {

        /**
         * Creates a new instance
         * @param team to be shown checklist items for
         * @param match to be shown checklist items for
         * @return A new instance of fragment [ChecklistFragment].
         */
        fun newInstance(team: Team, match: Match?): ChecklistFragment
        {
            val fragment = ChecklistFragment()
            val args = Bundle()
            args.putString(ARG_TEAM_JSON, toJson(team))
            args.putString(ARG_MATCH_JSON, toJson(match))
            fragment.arguments = args
            return fragment
        }
    }
}