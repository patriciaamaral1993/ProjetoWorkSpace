<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Tarefa;

class TarefaController extends Controller
{
  public function index() {
    try {
		$tarefas = Tarefa::all();
		return response()->json($tarefas, 201);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não foi possível aceder à lista de tarefas.", 500);
	}
  }

  public function show($id) {
	try {
		$tarefa = Tarefa::findOrFail($id);
		return response()->json($tarefa, 201);
	} catch (ModelNotFoundException $e) { // Tarefa not found
		return response()->json("Parâmetro ID com erros.", 422);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não é possível mostrar os dados da tarefa pretendida.", 500);
	}
  }

  public function store(Request $request) {
	try {
		$tarefa = Tarefa::create($request->all());
		return response()->json($tarefa, 201);
	} catch (ModelNotFoundException $e) { // Tarefa not found
		return response()->json("Erros nos parametrod rec", 422);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não é possível mostrar os dados da tarefa pretendida.", 500);
	}
  }

  public function update(Request $request, $id) {
	try {
		$tarefa = Tarefa::findOrFail($id);
    	$tarefa = $tarefa->update($request->all());
		return response()->json($tarefa, 201);
	} catch (ModelNotFoundException $e) { // Tarefa not found
		return response()->json("Erros nos parametrod rec", 422);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não é possível mostrar os dados da tarefa pretendida.", 500);
	}
  }

  public function delete($id) {
	try {
		$tarefa = Tarefa::findOrFail($id);
    	$tarefa->delete();
		return response()->json("Tarefa apagada com sucesso.", 201);
	} catch (ModelNotFoundException $e) { // Tarefa not found
		return response()->json("Erros nos parametrod rec", 422);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não é possível mostrar os dados da tarefa pretendida.", 500);
	}
  }
}