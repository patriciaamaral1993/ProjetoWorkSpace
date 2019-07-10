<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Proprietario;

class ProprietarioController extends Controller
{
  public function index() {
    try {
		$Proprietario = Proprietario::all();
		return response()->json($Proprietario, 201);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não foi possível aceder à lista de Proprietarios.", 500);
	}
  }

  public function show($id) {
	try {
		$Proprietario = Proprietario::findOrFail($id);
		return response()->json($Proprietario, 201);
	} catch (ModelNotFoundException $e) { // proprietario not found
		return response()->json("Parâmetro ID com erros.", 422);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não é possível mostrar os dados do Proprietario pretendido.", 500);
	}
  }

  public function store(Request $request) {
	try {
		$Proprietario = Proprietario::create($request->all());
		return response()->json($Proprietario, 201);
	} catch (ModelNotFoundException $e) { // proprietario not found
		return response()->json("Erros nos parametros pedidos", 422);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não é possível mostrar os dados do Proprietario pretendido.", 500);
	}
  }

  public function update(Request $request, $id) {
	try {
		$proprietario = Proprietario::findOrFail($id);
    	$proprietario = $proprietario->update($request->all());
		return response()->json($proprietario, 201);
	} catch (ModelNotFoundException $e) { // Proprietario not found
		return response()->json("Erros nos parametros pedidos", 422);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não é possível mostrar os dados do proprietario pretendido.", 500);
	}
  }

  public function delete($id) {
	try {
		$proprietario = proprietario::findOrFail($id);
    	$proprietario->delete();
		return response()->json("proprietario apagado com sucesso.", 201);
	} catch (ModelNotFoundException $e) { // proprietario not found
		return response()->json("Erros nos parametros pedidos", 422);
	} catch (Exception $e) { // Anything that went wrong
		return response()->json("Não é possível mostrar os dados do proprietario pretendido.", 500);
	}
  }
}